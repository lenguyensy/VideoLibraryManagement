package sy.config;

import java.sql.PreparedStatement;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Cache {
	public static final String REDIS_NAMESPACE_MOVIE = "movie.";
	public static final String REDIS_NAMESPACE_USER = "user.";
	public static final String REDIS_NAMESPACE_RENTAL = "rental.";
	public static final int EXPIRED_SECONDS = 60 * 10;// used for time based (15
														// minutes)

	private static Jedis jedis;

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
			String.valueOf(stmt.toString().hashCode());
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
		jedis = MainConfig.getRedisConnection();

		try {
			String combinedKey = namespace + key;
			System.out.println("GET CACHE: " + combinedKey);
			String val = jedis.get(combinedKey);
			System.out.println(val);
			return val;
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			jedis.disconnect();
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
		jedis = MainConfig.getRedisConnection();

		try {
			String combinedKey = namespace + key;
			System.out.println("UPDATE CACHE: " + combinedKey);
			System.out.println(value);

			jedis.set(combinedKey, value);

			// set expiration
			jedis.expire(combinedKey, EXPIRED_SECONDS);

			// append to namespace (update list of all previous cache)
			jedis.sadd(namespace, combinedKey);
		} catch (Exception e) {
			// handle exception...
		} finally {
			jedis.disconnect();
		}
	}

	/**
	 * clear cache
	 * 
	 * @param namespace
	 */
	public static void clear(String namespace) {
		jedis = MainConfig.getRedisConnection();

		try {
			System.out.println("CLEAR CACHE: " + namespace);
			Set<String> sOld = jedis.smembers(namespace);
			jedis.del(namespace);

			for (String curKey : sOld) {
				jedis.del(curKey);
			}

			System.out.println(MainConfig.combine((String[]) sOld.toArray(),
					", "));
		} catch (Exception ex) {

		} finally {
			jedis.disconnect();
		}
	}
}
