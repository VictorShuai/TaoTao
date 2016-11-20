package org.vs.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vs.taotao.service.PictureService;
import org.vs.taotao.utils.JsonUtils;

@Controller
@RequestMapping("/pic")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile) {
		Map<String, Object> resultMap = this.pictureService.uploadFile(uploadFile);
		String result = JsonUtils.objectToJson(resultMap);
		return result;
	}
	
}
