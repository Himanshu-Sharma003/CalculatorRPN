package com.calculator.commons.exception;

import com.calculator.cache.CacheManager;

/**
 * Thrown to indicate insufficient numbers of
 * operands for an operator.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class InsufficientParameterException extends Exception {

	/**
	 * The Constant serialVersionUID.
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new insufficient parameter exception.
	 *
	 * @param message
	 *            : Custom Message.
	 */
	public InsufficientParameterException(String message) {
		super(message);

	}

	/**
	 * Overloaded Constructor.Instantiates a new insufficient parameter
	 * exception.
	 *
	 * @param operator
	 *            : Type of Operator.
	 * @param index
	 *            : Position of Operator.
	 */
	public InsufficientParameterException(String operator, int index) {
		super(String.format(CacheManager.get("insufficient.parameter.err"), operator, index));
	}

	/**
	 * Overloaded Constructor.Instantiates a new insufficient parameter
	 * exception.
	 *
	 * @param message
	 *            : Custom message;
	 * @param operator
	 *            :Type of operator like +,-.
	 * @param index
	 *            : Position of Operator.
	 */
	public InsufficientParameterException(String message, String operator, Integer index) {
		super(String.format(message, operator, index));
	}

}
