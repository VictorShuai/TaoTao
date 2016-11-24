package org.vs.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.service.ContentService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent content) {
		TaotaoResult result = this.contentService.saveContent(content);
		return result;
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public Datagrid findDatagridPage(int page, int rows, long categoryId) {
		Datagrid datagrid = this.contentService.findDatagridPage(page, rows, categoryId);
		return datagrid;
	}
	
}
