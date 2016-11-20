package org.vs.taotao.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vs.taotao.service.PictureService;
import org.vs.taotao.utils.FtpUtil;
import org.vs.taotao.utils.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${ftp_host}")
	private String host;
	@Value("${ftp_port}")
	private Integer port;
	@Value("${ftp_username}")
	private String username;
	@Value("${ftp_password}")
	private String password;
	@Value("${ftp_base_path}")
	private String basePath;
	@Value("${ftp_url_prefix}")
	private String ftpUrlPrefix;
	

	@Override
	public Map<String, Object> uploadFile(MultipartFile file) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String filePath = new DateTime().toString("yyyy/MM/dd");
			String fileName = IDUtils.genImageName();
			fileName = fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			InputStream input = file.getInputStream();
			boolean status = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, input);
			if (status) {
				result.put("error", 0);
				result.put("url", this.ftpUrlPrefix+"/"+filePath+"/"+fileName);
			} else {
				result.put("error", 1);
				result.put("message", "图片上传失败，请联系管理员");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "图片上传失败，请联系管理员");
		}
		return result;
	}

}
