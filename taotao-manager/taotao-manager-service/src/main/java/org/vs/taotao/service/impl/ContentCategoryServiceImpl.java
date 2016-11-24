package org.vs.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbContentCategoryMapper;
import org.vs.taotao.model.TreeNode;
import org.vs.taotao.pojo.TbContentCategory;
import org.vs.taotao.pojo.TbContentCategoryExample;
import org.vs.taotao.pojo.TbContentCategoryExample.Criteria;
import org.vs.taotao.service.ContentCategoryService;
import org.vs.taotao.utils.TaotaoResult;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<TreeNode> findContentCategoryTreeNode(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = this.contentCategoryMapper.selectByExample(example);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>(list.size());
		for (TbContentCategory category : list) {
			TreeNode node = new TreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			if (category.getIsParent()) {
				node.setState("closed");
			}
			treeNodes.add(node);
		}
		return treeNodes;
	}

	@Override
	public TaotaoResult saveContentCategory(long parentId, String name) {
		// 创建新的ContentCategory
		TbContentCategory category = new TbContentCategory();
		category.setIsParent(false);
		category.setName(name);
		category.setParentId(parentId);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		// 保存
		this.contentCategoryMapper.insert(category);
		
		// 父节点需要变化
		TbContentCategory parent = this.contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			this.contentCategoryMapper.updateByPrimaryKey(parent);
		}
		
		return TaotaoResult.ok(category);
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		TbContentCategory contentCategory = this.contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		this.contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentCategory(long id) {
		// 删除当前节点的所有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		this.contentCategoryMapper.deleteByExample(example);
		
		// 获取当前要删除的节点对象
		TbContentCategory current = this.contentCategoryMapper.selectByPrimaryKey(id);
		
		// 删除当前节点
		this.contentCategoryMapper.deleteByPrimaryKey(id);
		
		// 拿到父节点
		TbContentCategory parent = this.contentCategoryMapper.selectByPrimaryKey(current.getParentId());
		// 拿到该父节点对应的子节点的数量
		long count = this.contentCategoryMapper.selectCountByParentId(current.getParentId());
		// 如果数量为0，则把父节点变成普通节点
		if (count == 0) {
			parent.setIsParent(false);
			this.contentCategoryMapper.updateByPrimaryKey(parent);
		}
		
		return TaotaoResult.ok();
	}
	
}
