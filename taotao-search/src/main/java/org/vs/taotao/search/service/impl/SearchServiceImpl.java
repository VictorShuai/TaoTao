package org.vs.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.search.dao.SearchDao;
import org.vs.taotao.search.pojo.SearchResult;
import org.vs.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		// 创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询条件
		solrQuery.setQuery(queryString);
		// 分页处理
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		// 设置默认搜索域
		solrQuery.set("df", "item_keywords");
		// 高亮处理
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<span style='color:red;'>");
		solrQuery.setHighlightSimplePost("</span>");
		
		// 执行查询
		SearchResult searchResult = this.searchDao.query(solrQuery);
		// 计算总页数
		long totalPage = searchResult.getTotalCount() / rows;
		if (searchResult.getTotalCount() % rows != 0) {
			totalPage ++;
		}
		searchResult.setTotalPage(totalPage);
		searchResult.setCurrentPage(page);
		
		return searchResult;
	}

}
