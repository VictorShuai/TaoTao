package org.vs.taotao.search.mapper;

import java.util.List;

import org.vs.taotao.search.pojo.SearchItem;

public interface SearchItemMapper {
	/**
	 * 从数据库中查询需要导入到索引库的数据
	 * @return
	 */
	public List<SearchItem> findDBItemList();
}
