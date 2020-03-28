package com.suneee.redis.lock;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;
*/

public class RedisLock {

	/*public static void main(String[] args) {
		RedisLock lock = new RedisLock();
		String value = lock.getLock("zg-lock", 10000, 5000);
		
		if(value != null) {
			System.out.println("抢购资源，update数据库");
		}
		lock.releaseLock("zg-lock", value);
		
	}

	public String getLock(String key, int timeout, int expire) {
		Jedis client = null;
		try {
			client = RedisManager.getClient();
			String value = UUID.randomUUID().toString();

			// timeout时间内没有获取到这把锁，是不是就是抢购失败
			long end = System.currentTimeMillis() + timeout;

			// 我们在这个timeout时间内可以反复去获取到这把锁
			while (System.currentTimeMillis() < end) {
				// 当key不存在的时候，才会把value存储到redis中
				// 如果key在redis中存在，那么setnx不做任何操作
				if (client.setnx(key, value) == 1) {
					// 这里需要对锁有一个失效时间设置
					client.expire(key, expire);

					return value;
				}

				// -1代表key没有设置失效时间
				if (client.ttl(key) == -1) {
					client.expire(key, expire);
				}

				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
		return null;
	}

	public boolean releaseLock(String key, String value) {
		Jedis client = RedisManager.getClient();

		try {

			System.out.println(value);

			while (true) {
				client.watch(key);
				// 如果值相等，就说明是当前线程去删除这把锁
				if (value.equals(client.get(key))) {
					Transaction transation = client.multi();
					transation.del(key);
					List<Object> exec = transation.exec();
					if (exec == null) {
						continue;
					}
					return true;
				}
				client.unwatch();
				break;
			}
		} catch (JedisException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return false;
	}*/
}
