package org.vs.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbItemParamMapper;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItemParam;
import org.vs.taotao.pojo.TbItemParamExample;
import org.vs.taotao.pojo.TbItemParamExample.Criteria;
import org.vs.taotao.service.ItemParamService;
import org.vs.taotao.utils.TaotaoResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult findItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		// List<TbItemParam> list = this.itemParamMapper.selectByExample(example);
		List<TbItemParam> list = this.itemParamMapper.selectByExampleWithBLOBs(example);
		if (list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult saveItemParam(long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setCreated(new Date());
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setUpdated(new Date());
		
		this.itemParamMapper.insert(itemParam);
		
		return TaotaoResult.ok();
	}

	@Override
	public Datagrid findItemParamDatagrid(Integer page, Integer rows) {
		
		// 分页对象
		PageHelper.startPage(page, rows);
		// 查询
		TbItemParamExample example = new TbItemParamExample();
		// List<TbItemParam> list = this.itemParamMapper.selectByExample(example);
		// 这里要使用withBLOBS
		List<TbItemParam> list = this.itemParamMapper.selectByExampleWithBLOBs(example);
		// PageInfo对象
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		Datagrid datagrid = new Datagrid();
		datagrid.setTotal(pageInfo.getTotal());
		datagrid.setRows(pageInfo.getList());
		
		return datagrid;
	}

}
