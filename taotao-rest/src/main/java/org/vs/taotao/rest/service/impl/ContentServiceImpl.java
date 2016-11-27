package org.vs.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbContentMapper;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.pojo.TbContentExample;
import org.vs.taotao.pojo.TbContentExample.Criteria;
import org.vs.taotao.rest.dao.RedisDao;
import org.vs.taotao.rest.service.ContentService;
import org.vs.taotao.utils.JsonUtils;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private RedisDao redisDao;
	
	@Value("${cache_index_ad}")
	private String cacheIndexAd;

	@Override
	public List<TbContent> findContentListByCid(long categoryId) {
		
		// 从缓存中取
		try {
			String value = this.redisDao.hget(cacheIndexAd, categoryId+"");
			if (StringUtils.isNotBlank(value)) {
				List<TbContent> list = JsonUtils.jsonToList(value, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		
		// 放入缓存
		try {
			String value = JsonUtils.objectToJson(list);
			this.redisDao.hset(cacheIndexAd, categoryId+"", value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
