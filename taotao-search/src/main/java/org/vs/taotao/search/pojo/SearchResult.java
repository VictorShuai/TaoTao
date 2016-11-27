package org.vs.taotao.search.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Solr搜索结果封装类
 * 
 * @author Administrator
 *
 */
public class SearchResult implements Serializable {

	// 商品列表
	private List<SearchItem> items;
	// 总记录数
	private long totalCount;
	// 总页数
	private long totalPage;
	// 当前页
	private long currentPage;

	public List<SearchItem> getItems() {
		return items;
	}

	public void setItems(List<SearchItem> items) {
		this.items = items;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

}
