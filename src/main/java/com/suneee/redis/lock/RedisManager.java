package com.suneee.redis.lock;

/*import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;*/

public class RedisManager {

	/*private static JedisPool jedisPool;
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(20);
		jedisPool = new JedisPool(config,"192.168.60.104",6379);

	}
	
	public static Jedis getClient() {
		if(jedisPool != null) {
			return jedisPool.getResource();
		}
		return null;
	}
	
	public static void returnResource(JedisPool pool,Jedis redis) {
		if(redis != null) {
			pool.returnResource(redis);
		}
	}*/
}
