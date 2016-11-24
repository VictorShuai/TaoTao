package org.vs.taotao.service;

import java.util.List;

import org.vs.taotao.model.TreeNode;
import org.vs.taotao.utils.TaotaoResult;

public interface ContentCategoryService {
	
	public List<TreeNode> findContentCategoryTreeNode(long parentId);

	public TaotaoResult saveContentCategory(long parentId, String name);

	public TaotaoResult updateContentCategory(long id, String name);

	public TaotaoResult deleteContentCategory(long id);
	
}
