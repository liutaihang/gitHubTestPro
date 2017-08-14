package liu.dao.BaseDao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.ShardedJedis;

public class RedisDataBase extends RedisUtil{
	
	public StringBuilder setTimeOut(String key, Map<String, String> value, int seconds) throws IOException{
		return set(key, value, seconds);
	}

	private StringBuilder set(String key, Map<String, String> value, int seconds) {
		StringBuilder str = new StringBuilder();
		try {
			str.append(jedis.hmset(key, value));
			str.append("," + jedis.expire(key, seconds));
		} finally{
			closeClient(jedis);
		}
		return str;
	}
	
	private void closeClient(ShardedJedis jedis){
		if(jedis != null){
			jedis.close();
		}
	}
	
	public Object getByKey(String key) throws ClassNotFoundException, IOException{
		byte[] byt = jedis.get(converTobyte(key));
		Object obj = (byt == null ? null : converToObj(byt));
		return obj;
	}
	
	public void append(String key, String value){
		
	}
	
	public List<String> hmget(String key, String fields){
		return jedis.hmget(key, fields);
	}
}
