package org.vs.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.vs.taotao.search.pojo.SearchResult;

public interface SearchDao {
	
	/**
	 * service层封装SolrQuery对象，dao层只负责查询
	 * @param solrQuery
	 * @return
	 * @throws Exception
	 */
	public SearchResult query(SolrQuery solrQuery) throws Exception;
	
}
