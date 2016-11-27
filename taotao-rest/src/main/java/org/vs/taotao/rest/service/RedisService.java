package org.vs.taotao.rest.service;

import org.vs.taotao.utils.TaotaoResult;

public interface RedisService {
	
	/**
	 * 删除首页广告缓存
	 * @param contentCategoryId
	 * @return
	 */
	public TaotaoResult deleteIndexAdCache(long contentCategoryId);
	
	/**
	 * 删除首页商品分类缓存
	 * @return
	 */
	public TaotaoResult deleteIndexItemCategoryCache();
	
}
