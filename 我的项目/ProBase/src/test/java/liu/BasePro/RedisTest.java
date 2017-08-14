package liu.BasePro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@SuppressWarnings("resource")
public class RedisTest {
	public static void main(String[] args) throws IOException {
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(20);
		poolConfig.setMaxIdle(10);
		poolConfig.setTimeBetweenEvictionRunsMillis(30000);
		poolConfig.setMinEvictableIdleTimeMillis(30000);
		poolConfig.setTestOnBorrow(true);
		JedisShardInfo jedisShardInfo = new JedisShardInfo("redis://127.0.0.1:6379/15");
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(jedisShardInfo);
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, shards);
		ShardedJedis jedis = jedisPool.getResource();
//		jedis.expire("", 1000);
//		System.out.println(jedis.set("newKey", "value"));
		byte [] byt = serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this");
		System.out.println(jedis.exists(serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this")));
		
		Object ob = deserialize(jedis.get(serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this")), Object.class);
		
		System.out.println(jedis.hgetAll("DEAD_MESSAGE").get("sss7988") + ob);
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
	
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		GZIPOutputStream gos = null;
		try {
			bos = new ByteArrayOutputStream();
			gos = new GZIPOutputStream(bos);
			os = new ObjectOutputStream(gos);
			os.writeObject(value);
			os.close();
			gos.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("serialize error");
		} finally {
			close(os);
			close(gos);
			close(bos);
		}
		return rv;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] in, Class<T> requiredType) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		GZIPInputStream gis = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				gis = new GZIPInputStream(bis);
				is = new ObjectInputStream(gis);
				rv = is.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("deserialize error");
		} finally {
			close(is);
			close(gis);
			close(bis);
		}
		return (T) rv;
	}
	
	private static void close(Closeable closeable) {
		if (closeable != null)
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("serialize close error");
			}
	}
}
