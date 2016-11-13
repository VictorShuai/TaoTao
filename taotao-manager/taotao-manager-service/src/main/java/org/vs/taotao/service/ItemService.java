package org.vs.taotao.service;

import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;

public interface ItemService {
	
	public TbItem findItemById(Long itemId);
	
	public Datagrid findItemDatagrid(int page, int rows);
}
