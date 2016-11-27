package org.vs.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.search.service.SearchItemService;
import org.vs.taotao.utils.TaotaoResult;

/**
 * 搜索管理rest
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manage")
public class SearchManageController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	/**
	 * 将数据库中商品数据同步到索引库中
	 * @return
	 */
	@RequestMapping("/sync")
	@ResponseBody
	public TaotaoResult sync() {
		TaotaoResult result = this.searchItemService.importDBData2Solr();
		return result;
	}
}
