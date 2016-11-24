package org.vs.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vs.taotao.model.TreeNode;
import org.vs.taotao.service.ContentCategoryService;
import org.vs.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService categoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> findCategoreyList(@RequestParam(value="id", defaultValue="0") long parentId) {
		List<TreeNode> treeNodes = this.categoryService.findContentCategoryTreeNode(parentId);
		return treeNodes;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult saveContentCategory(long parentId, String name) {
		TaotaoResult result = this.categoryService.saveContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(long id, String name) {
		TaotaoResult result = this.categoryService.updateContentCategory(id, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(long id) {
		TaotaoResult result = this.categoryService.deleteContentCategory(id);
		return result;
	}
	
}
