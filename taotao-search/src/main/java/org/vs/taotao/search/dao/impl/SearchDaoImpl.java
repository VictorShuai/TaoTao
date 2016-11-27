package org.vs.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vs.taotao.search.dao.SearchDao;
import org.vs.taotao.search.pojo.SearchItem;
import org.vs.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult query(SolrQuery solrQuery) throws Exception {
		// 查询
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList documentList = response.getResults();
		// 封装对象
		SearchResult result = new SearchResult();
		// 结果总数
		result.setTotalCount(documentList.getNumFound());
		// 将查询结果封装为SearchItem对象
		List<SearchItem> items = new ArrayList<SearchItem>();
		// 高亮结果
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument document : documentList) {
			SearchItem item = new SearchItem();
			item.setId(document.get("id").toString());
			item.setCategoryName(document.get("item_category_name").toString());
			item.setImage(document.get("item_image").toString());
			item.setPrice((Long) document.get("item_price"));
			item.setSellPoint(document.get("item_sell_point").toString());
			
			// 高亮处理
			String title = "";
			Map<String, List<String>> map = highlighting.get("id");
			if (map != null && !map.isEmpty()) {
				List<String> list = map.get("item_title");
				if (list == null || list.size() == 0) {
					title = document.get("title").toString();
				} else {
					title = list.get(0);
				}
			}
			
			item.setTitle(title);
			
			items.add(item);
		}
		result.setItems(items);
		return result;
	}

}
