package org.vs.taotao.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
	
	@Test
	public void test01() throws SolrServerException, IOException {
		// 向索引库中添加数据
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.2.136:8080/solr");
		// 创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "小米超薄手机");
		document.addField("item_price", 99888777);
		// 将文档写入到索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}
	
	@Test
	public void test02() throws SolrServerException, IOException {
		// 删除文档
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.2.136:8080/solr");
		solrServer.deleteById("test001");
		solrServer.commit();
	}
	
}
