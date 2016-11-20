package org.vs.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbItemCatMapper;
import org.vs.taotao.pojo.TbItemCat;
import org.vs.taotao.pojo.TbItemCatExample;
import org.vs.taotao.pojo.TbItemCatExample.Criteria;
import org.vs.taotao.rest.pojo.CatNode;
import org.vs.taotao.rest.pojo.CatResult;
import org.vs.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper  itemCatMapper;

	@Override
	public CatResult findItemCat() {
		CatResult catResult = new CatResult();
		catResult.setData(findCateNodeByParentId(0));
		return catResult;
	}
	
	private List<CatNode> findCateNodeByParentId(long parentId) {
		// 需要处理结果，一级目录14个就够了
		int count = 0;
		TbItemCatExample catExample = new TbItemCatExample();
		Criteria createCriteria = catExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = this.itemCatMapper.selectByExample(catExample);
		List nodes = new ArrayList();
		for (TbItemCat itemCat : list) {
			CatNode catNode = new CatNode();
			
			// 如果是父节点，需要特殊处理
			if (itemCat.getIsParent()) {
				// 如果是第一层节点，名称需要用<a>标签进行包裹
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
					count ++;
					if (count > 14) {
						break;
					}
				} else {
					catNode.setName(itemCat.getName());
				}
				// URL
				catNode.setUrl("/products/"+itemCat.getId()+".html");
				// 递归处理子节点
				catNode.setItem(findCateNodeByParentId(itemCat.getId()));
				nodes.add(catNode);
			} else {
				// 如果是子节点，直接添加字符串即可，如："/products/3.html|电子书"
				nodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
		}
		
		return nodes;
	}

}
