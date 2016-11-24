package org.vs.taotao.service;

import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.utils.TaotaoResult;

public interface ContentService {
	
	public TaotaoResult saveContent(TbContent content);

	public Datagrid findDatagridPage(int page, int rows, long categoryId);
	
}
