package org.vs.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbContentMapper;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.pojo.TbContentExample;
import org.vs.taotao.pojo.TbContentExample.Criteria;
import org.vs.taotao.service.ContentService;
import org.vs.taotao.utils.TaotaoResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public TaotaoResult saveContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		this.contentMapper.insert(content);
		return TaotaoResult.ok();
	}

	@Override
	public Datagrid findDatagridPage(int page, int rows, long categoryId) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		Datagrid datagrid = new Datagrid();
		datagrid.setTotal(info.getTotal());
		datagrid.setRows(info.getList());
		
		return datagrid;
	}

}
