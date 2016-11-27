package org.vs.taotao.rest.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	@Test
	public void test01() {
		// 测试单机版程序
		Jedis jedis = new Jedis("192.168.2.136", 6379);
		// 设置一个key
		jedis.set("key1", "100");
		// 获取
		String result = jedis.get("key1");
		System.out.println(result);
		// 关闭连接
		jedis.close();
	}
	
	@Test
	public void test02() {
		// 使用连接池
		JedisPool jedisPool = new JedisPool("192.168.2.136", 6379);
		// 从连接池中获取连接
		Jedis jedis = jedisPool.getResource();
		// 获取
		String result = jedis.get("key1");
		System.out.println(result);
		// 关闭连接，此时连接会返回到连接池中
		jedis.close();
	}
	
	@Test
	public void test03() {
		// 集群连接
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		set.add(new HostAndPort("192.168.2.136", 7001));
		set.add(new HostAndPort("192.168.2.136", 7002));
		set.add(new HostAndPort("192.168.2.136", 7003));
		set.add(new HostAndPort("192.168.2.136", 7004));
		set.add(new HostAndPort("192.168.2.136", 7005));
		set.add(new HostAndPort("192.168.2.136", 7006));
		JedisCluster jedisCluster = new JedisCluster(set);
		// 添加缓存数据
		jedisCluster.set("key1", "111111");
		// 获取数据
		String result = jedisCluster.get("key1");
		System.out.println(result);
		
		jedisCluster.close();
	}
	
	@Test
	public void test04() {
		// 单机版整合Spring
		// 获取连接池
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-cache.xml");
		JedisPool jedisPool = ctx.getBean(JedisPool.class);
		Jedis jedis = jedisPool.getResource();
		// 获取数据
		String result = jedis.get("key1");
		System.out.println(result);
		jedis.close();
	}
	
	@Test
	public void test05() {
		// 集群版整合Spring
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-cache.xml");
		JedisCluster jedisCluster = ctx.getBean(JedisCluster.class);
		// 获取数据
		String result = jedisCluster.get("key1");
		System.out.println(result);
	}
}
