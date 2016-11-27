package org.vs.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbContentMapper;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.pojo.TbContentExample;
import org.vs.taotao.pojo.TbContentExample.Criteria;
import org.vs.taotao.service.ContentService;
import org.vs.taotao.utils.HttpClientUtil;
import org.vs.taotao.utils.JsonUtils;
import org.vs.taotao.utils.TaotaoResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements ContentService {
	
	private static final Logger logger = Logger.getLogger(ContentServiceImpl.class);
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${rest_cache_url}")
	private String restCacheUrl;
	@Value("${rest_cache_clear_content}")
	private String restCacheClearContent;

	@Override
	public TaotaoResult saveContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		this.contentMapper.insert(content);
		
		// 调用服务清除缓存
		try {
			String result = HttpClientUtil.doGet(restCacheUrl+restCacheClearContent+"/"+content.getCategoryId());
			TaotaoResult taotaoResult = JsonUtils.jsonToPojo(result, TaotaoResult.class);
			if (taotaoResult.getStatus() != 200) {
				logger.error("【商品类目】缓存清理失败，" + taotaoResult.getMsg());
			} else {
				logger.debug("【商品类目】缓存清理成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public Datagrid findDatagridPage(int page, int rows, long categoryId) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		Datagrid datagrid = new Datagrid();
		datagrid.setTotal(info.getTotal());
		datagrid.setRows(info.getList());
		
		return datagrid;
	}

}
