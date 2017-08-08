package liu.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDataBase {

	public static ShardedJedis jedis = null;
	
	private ShardedJedis getJedis(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(20);
		poolConfig.setMaxIdle(10);
		poolConfig.setTimeBetweenEvictionRunsMillis(30000);
		poolConfig.setMinEvictableIdleTimeMillis(30000);
		poolConfig.setTestOnBorrow(true);
		JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost");
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(jedisShardInfo);
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, shards);
		return jedisPool.getResource();
	}
	
	public void setTimeOut(String key, Map value, int seconds){
		if(jedis == null){
			jedis = getJedis();
		}
		try {
			jedis.hmset(key, value);
			jedis.expire(key, seconds);
		} finally{
			closeClient(jedis);
		}
	}
	
	private void closeClient(ShardedJedis jedis){
		if(jedis != null){
			jedis.close();
		}
	}
	
	public void getByKey(String key, String fields){
		if(jedis == null){
			jedis = getJedis();
		}
		jedis.hmget(key, fields);
	}
}
