package org.vs.taotao.portal.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	
	// Get请求方式
	@Test
	public void testGetMethod() throws IOException {
		// 获取HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个GET请求对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 输出信息
		System.out.println("响应行状态码：" + response.getStatusLine().getStatusCode());
		// 获取内容
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity, "UTF-8");
		System.out.println(str);
		httpClient.close();
	}
	
	// Post请求方式
	@Test
	public void testPostMethod() throws Exception {
		// 获取HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// Post对象
		URIBuilder builder = new URIBuilder("http://localhost:8081/rest/content/new");
		builder.addParameter("username", "zhangshuai");
		builder.addParameter("password", "123456");
		HttpPost post = new HttpPost(builder.build());
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 获取相应内容
		HttpEntity entity = response.getEntity();
		System.out.println("响应内容：" + EntityUtils.toString(entity));
		httpClient.close();
	}
}	
