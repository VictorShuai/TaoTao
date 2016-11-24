package org.vs.taotao.rest.service;

import java.util.List;

import org.vs.taotao.pojo.TbContent;

public interface ContentService {
	
	public List<TbContent> findContentListByCid(long categoryId);
	
}
