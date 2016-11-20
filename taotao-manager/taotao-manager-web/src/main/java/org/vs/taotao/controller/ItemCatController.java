package org.vs.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.model.TreeNode;
import org.vs.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> loadItemCatTree(@RequestParam(value="id", defaultValue="0") long parentId) {
		List<TreeNode> list = this.itemCatService.findItemCatByParentId(parentId);
		return list;
	}
	
}
