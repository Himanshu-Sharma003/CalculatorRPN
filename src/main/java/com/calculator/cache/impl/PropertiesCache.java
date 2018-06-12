package com.calculator.cache.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import com.calculator.cache.ICache;
import com.calculator.commons.CalculatorHelper;

/**
 * Caches values stored in properties file. PropertiesCache provide access to
 * key/value by implementing Singleton design pattern.
 */
public class PropertiesCache implements ICache {

	/**
	 * Static Instance, eager initialization.
	 **/
	private static PropertiesCache cache = new PropertiesCache();

	/**
	 * Holds Key/Value pair.
	 */
	private final Properties properties = new Properties();

	/**
	 * Instantiates a new properties cache.
	 */
	private PropertiesCache() {
		super();
		loadProperties();
	}

	/**
	 * Static method that returns the instance.
	 *
	 * @return PropertiesCache
	 */
	public static PropertiesCache getInstance() {
		return cache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#get(java.lang.String)
	 */
	public String get(final String input) {
		return properties.getProperty(input);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#containsKey(java.lang.String)
	 */
	public Boolean containsKey(final String key) {
		return properties.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#getAllKeys()
	 */
	public Set<String> getAllKeys() {
		return properties.stringPropertyNames();
	}

	/**
	 * Load properties from Config.properties file.
	 */
	private void loadProperties() {
		CalculatorHelper.log(PropertiesCache.class.getName(), Level.FINEST, "loadProperties", "Loading Config.Properties File");
		InputStream inputStream = null;
		try {

			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			if (inputStream == null) {
				throw new FileNotFoundException("property file config.properties not found in the classpath");
			}
			properties.load(inputStream);
			CalculatorHelper.log(PropertiesCache.class.getName(), Level.FINEST, "loadProperties",
					"Config.Properties file loaded successfully.");
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(final String key, final Object value) {
		throw new UnsupportedOperationException("This operation is not supported");
	}

}
