package com.calculator.cache;

import java.util.Set;

/**
 * Interface for Caching.
 *
 * @author Himanshu Sharma.
 */
public interface ICache {

	/**
	 * Fetch value for the specified key.
	 *
	 * @param key
	 *            : key
	 * @return the object
	 */
	Object get(String key);

	/**
	 * Adds the Key/Value pair to cache.
	 *
	 * @param key
	 *            : Key
	 * @param value
	 *            : Value
	 */
	void put(String key, Object value);

	/**
	 * Checks if Key exists in given cache.
	 *
	 * @param key
	 *            : Key
	 * @return the boolean
	 */
	Boolean containsKey(String key);

	/**
	 * Collection of all the keys from cache.
	 *
	 * @return the all keys
	 */
	Set<String> getAllKeys();

}