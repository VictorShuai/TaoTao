package org.vs.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.rest.pojo.CatResult;
import org.vs.taotao.rest.service.ItemCatService;
import org.vs.taotao.utils.JsonUtils;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	// 第一种方式
	/*@RequestMapping(value="/itemcat/all", produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public String findItemCat(String callback) {
		CatResult catResult = itemCatService.findItemCat();
		// 转换为JSON
		String json = JsonUtils.objectToJson(catResult);
		// 处理callback调用
		String result = callback+"("+json+")";
		return result;
	}*/
	
	// 第二种方式
	@RequestMapping(value="/itemcat/all", produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public Object findItemCat(String callback) {
		CatResult catResult = itemCatService.findItemCat();
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		jacksonValue.setJsonpFunction(callback);
		
		return jacksonValue;
	}
}
