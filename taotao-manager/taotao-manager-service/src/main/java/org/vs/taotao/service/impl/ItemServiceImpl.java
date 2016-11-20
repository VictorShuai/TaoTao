package org.vs.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbItemDescMapper;
import org.vs.taotao.mapper.TbItemMapper;
import org.vs.taotao.mapper.TbItemParamItemMapper;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;
import org.vs.taotao.pojo.TbItemDesc;
import org.vs.taotao.pojo.TbItemExample;
import org.vs.taotao.pojo.TbItemParamItem;
import org.vs.taotao.service.ItemService;
import org.vs.taotao.utils.IDUtils;
import org.vs.taotao.utils.TaotaoResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

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

	@Override
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) {
		
		// 商品数据
		item.setId(IDUtils.genItemId());
		item.setCreated(new Date());
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		
		this.itemMapper.insert(item);
		
		// 描述数据
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId()); // 描述信息ID与商品ID一致
		itemDesc.setUpdated(new Date());
		
		this.itemDescMapper.insert(itemDesc);
		
		// 规格参数
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemParams);
		itemParamItem.setUpdated(new Date());
		
		this.itemParamItemMapper.insert(itemParamItem);
		
		return TaotaoResult.ok();
	}

}
