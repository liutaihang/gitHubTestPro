package liu.BasePro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@SuppressWarnings("resource")
public class RedisTest {
	@SuppressWarnings({ "unchecked", "unused" })
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
		for (Map<String, String> map : setSome()) {
			System.out.println(jedis.hmset(map.get("infoSeqNo") + map.get("userid"), map));
		}
		
//		jedis.expire("", 1000);
//		System.out.println(jedis.set("newKey", "value"));
		byte [] byt = serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this");
//		System.out.println(jedis.exists(serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this")));
		
		Object ob = deserialize(jedis.get(serialize("laiyongche_LYC_AUTH_ONLINE_USER_NAME_this")), Object.class);
		
//		System.out.println(jedis.hgetAll("DEAD_MESSAGE").get("sss7988") + ob);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List<Map> setSome(){
			Map poster = new HashMap();  
	        poster.put("userid", "user001");  
	        poster.put("userName", "Lily");  
	        poster.put("infoSeqNo", "INFO20150815");  
	        poster.put("postTime", "2015-08-15 20:26:30");  
	        poster.put("info", "今天很开心");  
	        poster.put("infoId", "info001");  
	          
	        Map respondent1 = new HashMap();  
	        respondent1.put("userid", "user002");  
	        respondent1.put("userName", "Tom");  
	        respondent1.put("infoSeqNo", "INFO20150815");  
	        respondent1.put("answerTime", "2015-08-15 20:28:40");  
	        respondent1.put("info", "是吗");  
	        respondent1.put("replyTarget", "user001");  
	        respondent1.put("infoId", "info002");  
	          
	        Map respondent2 = new HashMap();  
	        respondent2.put("userid", "user003");  
	        respondent2.put("userName", "Kay");  
	        respondent2.put("infoSeqNo", "INFO20150815");  
	        respondent2.put("answerTime", "2015-08-15 20:30:27");  
	        respondent2.put("info", "她肯定很开心啦，因为……");  
	        respondent2.put("replyTarget", "user002");  
	        respondent2.put("infoId", "info003");  
	          
	        Map respondent3 = new HashMap();  
	        respondent3.put("userid", "user001");  
	        respondent3.put("userName", "Lily");  
	        respondent3.put("infoSeqNo", "INFO20150815");  
	        respondent3.put("answerTime", "2015-08-15 20:38:40");  
	        respondent3.put("info", "恩，今天去玩了");  
	        respondent3.put("replyTarget", "user003");  
	        respondent3.put("infoId", "info004");  
	        List<Map> lists = new ArrayList<>();
	        lists.add(poster);
	        lists.add(respondent3);
	        lists.add(respondent2);
	        lists.add(respondent1);
	        return lists;
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
