package org.vs.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vs.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		String indexAdJson = this.contentService.findIndexContent();
		model.addAttribute("indexAd", indexAdJson);
		return "index";
	}
	
}
