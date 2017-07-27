package liu.BasePro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisTest {
	public static void main(String[] args) throws IOException {
		
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
		ShardedJedis jedis = jedisPool.getResource();
		
		
//		System.out.println(jedis.set("newKey", "value"));
		System.out.println(jedis.hgetAll("DEAD_MESSAGE").get("sss7988"));
	}
	

	public static byte[] converTobyte(Object obj) throws IOException{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutputStream object = new ObjectOutputStream(stream);
		object.writeObject(obj);
		byte[] bytes = stream.toByteArray();
		return bytes;
	}
	
	public static Object converToObj(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		ObjectInputStream object = new ObjectInputStream(stream);
		return object.readObject();
	}
}
