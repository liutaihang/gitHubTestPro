package com.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClientTest {

	public static void main(String[] args) throws IOException {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		System.out.println(jedis.select(15));
		Pipeline pipeline = jedis.pipelined();
		List<String> keys = new ArrayList<String>(jedis.keys("INFO20150815*"));
		for (String key : keys) {
			System.out.println(key);
			pipeline.incrBy(key, 2);
			Response<Map<String, String>> res = pipeline.hgetAll(key);
			pipeline.close();
			System.out.println(res.get().toString());
		};
		ShardedJedisPool jedisPool = new ShardedJedisPool(null, null);
//		frist : {
//			second : {
//				thrid : {
//					System.out.println("first3");
//					if (true) break second;
//				}
//			System.out.println("first2");
//			}
//		System.out.println("first");
//		}
//		int num = 2;
//		do {
//			System.out.println(num--);
//		} while (num > 2);
	}
	
}
