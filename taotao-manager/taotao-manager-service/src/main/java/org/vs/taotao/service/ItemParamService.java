package org.vs.taotao.service;

import org.vs.taotao.model.Datagrid;
import org.vs.taotao.utils.TaotaoResult;

public interface ItemParamService {
	
	public TaotaoResult findItemParamByCid(long cid);

	public TaotaoResult saveItemParam(long cid, String paramData);

	public Datagrid findItemParamDatagrid(Integer page, Integer rows);
	
}
