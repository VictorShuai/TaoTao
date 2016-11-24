package org.vs.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vs.taotao.pojo.TbContent;
import org.vs.taotao.portal.service.ContentService;
import org.vs.taotao.utils.HttpClientUtil;
import org.vs.taotao.utils.JsonUtils;
import org.vs.taotao.utils.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${rest_base_url}")
	public String restBaseUrl;
	
	@Value("${rest_index_banner_uri}")
	public String restIndexBannerUri;

	@Override
	public String findIndexContent() {
		
		// 使用http获取rest数据
		String restData = HttpClientUtil.doPost(restBaseUrl + restIndexBannerUri);
		// 将数据转换为TaotaoResult
		TaotaoResult taotaoResult = TaotaoResult.formatToList(restData, TbContent.class);
		// 拿到Data，封装为前台所需pojo格式
		List<TbContent> list = (List<TbContent>) taotaoResult.getData();
		List<Map> pojos = new ArrayList<Map>();
		for (TbContent content : list) {
			Map map = new HashMap();
			map.put("src", content.getPic());
			map.put("width", 670);
			map.put("height", 240);
			map.put("srcB", content.getPic2());
			map.put("widthB", 550);
			map.put("heightB", 240);
			map.put("href", content.getUrl());
			map.put("alt", content.getTitle());
			pojos.add(map);
		}
		
		// 转换为json
		String result = JsonUtils.objectToJson(pojos);
		
		return result;
	}

}
