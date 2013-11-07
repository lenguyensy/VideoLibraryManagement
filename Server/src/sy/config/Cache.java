package sy.config;

import java.sql.PreparedStatement;

import redis.clients.jedis.Jedis;

public class Cache {
	public static final String REDIS_NAMESPACE_MOVIE = "movie.";
	public static final String REDIS_NAMESPACE_USER = "user.";
	public static final String REDIS_NAMESPACE_RENTAL = "rental.";
	public static final String REDIS_HOST = "localhost";// default 6379

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
		System.out.println("GET CACHE: " + namespace + key);
		
		Jedis jedis = new Jedis(REDIS_HOST);
		return jedis.get(namespace + key);
	}

	/**
	 * set cache
	 * 
	 * @param namespace
	 * @param key
	 * @param value
	 */
	public static void set(String namespace, String key, String value) {
		System.out.println("UPDATE CACHE: " + namespace + key);
		Jedis jedis = new Jedis(REDIS_HOST);
		jedis.set(namespace + key, value);

		// append to namespace (update list of all previous cache)
		String old = jedis.get(namespace);
		if (old == null)
			old = "";

		if (old.indexOf(key) == -1) {
			old += key + ",";

			jedis.set(namespace, old);
		}
	}

	/**
	 * clear cache
	 * 
	 * @param namespace
	 */
	public static void clear(String namespace) {
		Jedis jedis = new Jedis(REDIS_HOST);
		String old = jedis.get(namespace);
		String[] keys = old.split(",");
		for (int i = 0; i < keys.length; i++)
			jedis.set(keys[i], null);
	}
}
