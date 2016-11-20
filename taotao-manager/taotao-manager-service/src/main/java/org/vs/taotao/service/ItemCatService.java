package org.vs.taotao.service;

import java.util.List;

import org.vs.taotao.model.TreeNode;

public interface ItemCatService {
	
	public List<TreeNode> findItemCatByParentId(long parentId);
	
}
