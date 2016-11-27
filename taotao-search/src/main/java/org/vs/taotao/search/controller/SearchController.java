package org.vs.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.search.pojo.SearchResult;
import org.vs.taotao.search.service.SearchService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult query(@RequestParam("q") String queryString,
								@RequestParam(defaultValue="1") int page,
								@RequestParam(defaultValue="60") int rows) {
		// 确定查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		SearchResult result = null;
		try {
			// 中文乱码处理
			queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");
			result = this.searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		return TaotaoResult.ok(result);
	}
}
