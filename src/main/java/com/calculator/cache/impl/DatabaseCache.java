package com.calculator.cache.impl;

import java.util.Set;

import com.calculator.cache.ICache;

/**
 * Class for implementing Database caching. Currently Database caching is not in
 * scope ; hence proper implementation for methods are not provided.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class DatabaseCache implements ICache {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#get(java.lang.String)
	 */
	@Override
	public Object get(final String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(final String key, final Object value) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#containsKey(java.lang.String)
	 */
	@Override
	public Boolean containsKey(final String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.cache.ICache#getAllKeys()
	 */
	@Override
	public Set<String> getAllKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
