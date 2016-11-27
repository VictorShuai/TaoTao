package org.vs.taotao.search.service;

import org.vs.taotao.utils.TaotaoResult;

public interface SearchItemService {
	
	/**
	 * 将商品数据导入到solr索引库
	 * @return
	 */
	public TaotaoResult importDBData2Solr();
	
}
