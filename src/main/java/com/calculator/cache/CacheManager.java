package com.calculator.cache;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.calculator.cache.impl.PropertiesCache;
import com.calculator.commons.CalculatorHelper;


/**
 * Factory implementation for Cache Management.
 */
/**
 * @author Himanshu Sharma
 *
 */
public class CacheManager {

	/**
	 * Map to hold various implementation of Caching
	 */
	private static Map<Class<?>, ICache> caches = new HashMap<>();

	/**
	 * Returns Cache instance from Map, if instance does not exits creates one
	 * by invoking getInstance method of individual Cache implementation.
	 *
	 * @param cacheClass
	 *            : Caching Implementation Class
	 * @return the cache
	 */
	private static ICache getCache(final Class<?> cacheClass) {
		final Boolean isICacheType = isICacheType(cacheClass);
		ICache cache = null;
		if (isICacheType) {
			if (caches.containsKey(cacheClass)) {
				cache = caches.get(cacheClass);
			} else {
				try {
					CalculatorHelper.log(CacheManager.class.getName(), Level.FINEST, "getCache","Initalizing Cache : " + cacheClass.getName());
					cache = (ICache) cacheClass.getDeclaredMethod("getInstance").invoke(null);
					caches.put(cacheClass, cache);
					CalculatorHelper.log(CacheManager.class.getName(), Level.FINEST, "getCache","Cache Initalized Successfully : " + cacheClass.getName());
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
					throw new IllegalStateException(ex.getMessage(), ex);
				}
			}
		} else {
			throw new IllegalStateException(
					String.format("Class \"%s\" not supported yet.", cacheClass.getClass().getName()));
		}
		return cache;
	}

	/**
	 * Checks if the passed class is implementing ICache interface.
	 *
	 * @param cacheClass
	 *            Class type
	 * @return the boolean
	 */
	private static Boolean isICacheType(final Class<?> cacheClass) {
		Boolean isICacheType = false;
		final Class<?>[] interfaces = cacheClass.getInterfaces();
		for (final Class<?> cacheInterface : interfaces) {
			if (cacheInterface.equals(ICache.class)) {
				isICacheType = true;
				break;
			}
		}
		return isICacheType;
	}

	/**
	 * Fetch value for the key from specified Cache type
	 *
	 * @param cacheClass
	 *            : Cache Type.
	 * @param key
	 *            Key for Cache elements.
	 * @return the object
	 */
	public static Object get(final Class<?> cacheClass, final String key) {
		final ICache cache = getCache(cacheClass);
		return cache.get(key);
	}

	/**
	 * Fetch value for specified key from Properties Cache.
	 *
	 * @param key
	 *            Key for Cache elements.
	 * @return the string
	 */
	public static String get(final String key) {
		final ICache propertiesCache = getCache(PropertiesCache.class);
		return (String) propertiesCache.get(key);
	}
	
}
