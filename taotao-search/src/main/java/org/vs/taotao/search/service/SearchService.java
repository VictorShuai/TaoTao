package org.vs.taotao.search.service;

import org.vs.taotao.search.pojo.SearchResult;

public interface SearchService {
	
	/**
	 * 检索商品
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 */
	public SearchResult search(String queryString, int page, int rows) throws Exception;
	
}
