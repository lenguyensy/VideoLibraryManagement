package sy.config;

import java.sql.PreparedStatement;

import redis.clients.jedis.Jedis;

public class Cache {
	public static final String REDIS_NAMESPACE_MOVIE = "movie.";
	public static final String REDIS_NAMESPACE_USER = "user.";
	public static final String REDIS_NAMESPACE_RENTAL = "rental.";
	public static final int EXPIRED_SECONDS = 60 * 15;//used for time based (15 minutes)

	private static Jedis jedis = MainConfig.getRedisConnection();
	
	public static String getKey(PreparedStatement stmt) {
		String md5 = stmt.toString().substring(stmt.toString().indexOf(": "));
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * get cache
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String namespace, String key) {
		String combinedKey = namespace + key;
		System.out.println("GET CACHE: " + combinedKey);
		try {
			return jedis.get(combinedKey);
		}catch ( Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

	/**
	 * set cache
	 * 
	 * @param namespace
	 * @param key
	 * @param value
	 */
	public static void set(String namespace, String key, String value) {
		String combinedKey = namespace + key;
		
		System.out.println("UPDATE CACHE..: " + combinedKey );
		try{
			jedis.set(combinedKey, value);
			
			//set expiration
			jedis.expire(combinedKey, EXPIRED_SECONDS);
	
			// append to namespace (update list of all previous cache)
			String old = jedis.get(namespace);
			if (old == null)
				old = "";
	
			if (old.indexOf(combinedKey) == -1) {
				old += combinedKey + ",";
	
				jedis.set(namespace, old);
			}
		} catch ( Exception e) {
			//handle exception...
		}
	}

	/**
	 * clear cache
	 * 
	 * @param namespace
	 */
	public static void clear(String namespace) {
		System.out.println("CLEAR CACHE: " + namespace);

		String old = jedis.get(namespace);
		String[] keys = old.split(",");
		for (int i = 0; i < keys.length; i++)
			jedis.del(keys[i]);
		
		//set namesapce to be empty now
		jedis.set(namespace, "");
	}
}
