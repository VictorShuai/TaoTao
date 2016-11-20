package org.vs.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vs.taotao.model.Datagrid;
import org.vs.taotao.pojo.TbItem;
import org.vs.taotao.service.ItemService;
import org.vs.taotao.utils.FtpUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class CommonTest {
	
	@Autowired
	private ItemService itemService;
	
	@Test
	public void testPageDatagrid() {
		int page = 1;
		int rows = 10;
		Datagrid datagrid = itemService.findItemDatagrid(page, rows);
		System.out.println("总记录数: " + datagrid.getTotal());
		List<TbItem> list = (List<TbItem>) datagrid.getRows();
		for (TbItem item : list) {
			System.out.println(item.getTitle());
		}
	}
	
	@Test
	public void testFtpUpload() throws IOException {
		FileInputStream input = new FileInputStream(new File("c:\\aaa.jpg"));
		boolean status = FtpUtil.uploadFile("192.168.2.136", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "2016/11/20", "abc.jpg", input);
		System.out.println("status: " + status);
	}
	
}
