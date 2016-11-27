package org.vs.taotao.rest.dao;

public interface RedisDao {
	
	/**
	 * 获取数据
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 添加数据
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value);
	
	/**
	 * 获取数据
	 * @param hkey
	 * @param key
	 * @return
	 */
	public String hget(String hkey, String key);
	
	/**
	 * 设置数据
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	public long hset(String hkey, String key, String value);
	
	/**
	 * 自增
	 * @param key
	 * @return
	 */
	public long incr(String key);
	
	/**
	 * 自减
	 * @param key
	 * @return
	 */
	public long decr(String key);
	
	/**
	 * 设置过期
	 * @param key
	 * @param second
	 * @return
	 */
	public long expire(String key, int second);
	
	/**
	 * 查看过期剩余时间
	 * @param key
	 * @return
	 */
	public long ttl(String key);
	
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	public long del(String key);
	
	/**
	 * 删除缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	public long hdel(String hkey, String key);
}
