package org.vs.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;
import org.vs.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	// 查询单个
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem = this.itemService.findItemById(itemId);
		return tbItem;
	}
	
	// 查询总数
	@RequestMapping("/list")
	@ResponseBody
	public Datagrid getPageDatagrid(int page, int rows) {
		Datagrid datagrid = this.itemService.findItemDatagrid(page, rows);
		return datagrid;
	}
	
}
