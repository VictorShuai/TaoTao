package org.vs.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	
	public Map<String, Object> uploadFile(MultipartFile file);
	
}
