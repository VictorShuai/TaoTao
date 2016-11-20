package org.vs.taotao.service;

import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;
import org.vs.taotao.utils.TaotaoResult;

public interface ItemService {
	
	public TbItem findItemById(Long itemId);
	
	public Datagrid findItemDatagrid(int page, int rows);

	public TaotaoResult saveItem(TbItem item, String desc, String itemParams);
}
