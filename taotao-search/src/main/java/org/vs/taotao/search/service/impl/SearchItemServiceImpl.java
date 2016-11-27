package org.vs.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.search.mapper.SearchItemMapper;
import org.vs.taotao.search.pojo.SearchItem;
import org.vs.taotao.search.service.SearchItemService;
import org.vs.taotao.utils.TaotaoResult;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult importDBData2Solr() {
		// 查询数据库商品数据
		List<SearchItem> list = this.searchItemMapper.findDBItemList();
		// 将商品数据转换为文档，添加到solr索引库
		try {
			for (SearchItem searchItem : list) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSellPoint());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategoryName());
				document.addField("item_desc", searchItem.getItemDesc());
				
				this.solrServer.add(document);
			}
			
			this.solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		
		
		return TaotaoResult.ok();
	}

}
