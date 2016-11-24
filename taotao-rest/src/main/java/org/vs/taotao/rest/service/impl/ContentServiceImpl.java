package org.vs.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.taotao.mapper.TbContentMapper;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.pojo.TbContentExample;
import org.vs.taotao.pojo.TbContentExample.Criteria;
import org.vs.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> findContentListByCid(long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = this.contentMapper.selectByExample(example);
		return list;
	}

}
