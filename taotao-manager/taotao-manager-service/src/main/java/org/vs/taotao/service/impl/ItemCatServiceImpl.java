package org.vs.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbItemCatMapper;
import org.vs.taotao.model.TreeNode;
import org.vs.taotao.pojo.TbItemCat;
import org.vs.taotao.pojo.TbItemCatExample;
import org.vs.taotao.pojo.TbItemCatExample.Criteria;
import org.vs.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TreeNode> findItemCatByParentId(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = this.itemCatMapper.selectByExample(example);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>(list.size());
		for (TbItemCat itemCat : list) {
			TreeNode node = new TreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			if (itemCat.getIsParent()) {
				node.setState("closed");
			}
			treeNodes.add(node);
		}
		return treeNodes;
	}

}
