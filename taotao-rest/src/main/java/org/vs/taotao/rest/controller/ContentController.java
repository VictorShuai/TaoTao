package org.vs.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.rest.service.ContentService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult findContentByCid(@PathVariable long contentCategoryId) {
		try {
			List<TbContent> list = this.contentService.findContentListByCid(contentCategoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
	
	@RequestMapping(value="/new")
	@ResponseBody
	public TaotaoResult test(String username, String password) {
		System.out.println("rest服务端：" + username + "   " + password);
		return TaotaoResult.ok();
	}
	
}
