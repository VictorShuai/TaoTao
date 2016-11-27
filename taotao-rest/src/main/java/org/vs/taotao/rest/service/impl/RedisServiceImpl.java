package org.vs.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vs.taotao.rest.dao.RedisDao;
import org.vs.taotao.rest.service.RedisService;
import org.vs.taotao.utils.TaotaoResult;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisDao redisDao;
	
	@Value("${cache_index_ad}")
	private String cacheIndexAd;
	@Value("${cache_index_category}")
	private String cacheIndexCategory;

	@Override
	public TaotaoResult deleteIndexAdCache(long contentCategoryId) {
		try {
			this.redisDao.hdel(cacheIndexAd, contentCategoryId+"");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteIndexItemCategoryCache() {
		try {
			this.redisDao.del(cacheIndexCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		return TaotaoResult.ok();
	}

}
