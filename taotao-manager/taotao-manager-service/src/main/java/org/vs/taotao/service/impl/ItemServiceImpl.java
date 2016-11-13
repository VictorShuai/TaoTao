package org.vs.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbItemMapper;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;
import org.vs.taotao.pojo.TbItemExample;
import org.vs.taotao.service.ItemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem findItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Datagrid findItemDatagrid(int page, int rows) {
		
		TbItemExample example = new TbItemExample();
		// 没有查询条件
		// 设置分页信息
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example );
		// 获取PageInfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 构建Datagrid对象
		Datagrid datagrid = new Datagrid();
		datagrid.setTotal(pageInfo.getTotal());
		datagrid.setRows(list);
		return datagrid;
	}

}
