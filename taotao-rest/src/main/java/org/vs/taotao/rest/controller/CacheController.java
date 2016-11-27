package org.vs.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.rest.service.RedisService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/del/indexad/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult deleteIndexAdCache(@PathVariable long contentCategoryId) {
		TaotaoResult result = this.redisService.deleteIndexAdCache(contentCategoryId);
		return result;
	}
	
	@RequestMapping("/del/itemcategory")
	@ResponseBody
	public TaotaoResult deleteItemCatetoryCache() {
		TaotaoResult result = this.redisService.deleteIndexItemCategoryCache();
		return result;
	}
	
}
