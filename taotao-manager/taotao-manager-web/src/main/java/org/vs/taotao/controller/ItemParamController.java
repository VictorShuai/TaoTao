package org.vs.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.service.ItemParamService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService  itemParamService;
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult findItemParamByCid(@PathVariable long cid) {
		TaotaoResult result = this.itemParamService.findItemParamByCid(cid);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable long cid, String paramData) {
		TaotaoResult result = this.itemParamService.saveItemParam(cid, paramData);
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Datagrid findList(Integer page, Integer rows) {
		Datagrid datagrid = this.itemParamService.findItemParamDatagrid(page, rows);
		return datagrid;
	}
}
