package com.calculator.commons.exception;

import com.calculator.cache.CacheManager;

/**
 * Thrown to indicate invalid operand for operator.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new invalid input exception.
	 *
	 * @param message
	 *            : Message
	 */
	public InvalidInputException(final String message) {
		super(message);

	}

	/**
	 * Overloaded Constructor. Instantiates a new invalid input exception.
	 *
	 * @param operator
	 *            : Operator with Invalid Operand.
	 * @param index
	 *            : Position of Operator.
	 */
	public InvalidInputException(final String operator, final Integer index) {
		super(String.format(CacheManager.get("invalid.input.operation.err"), operator, index));
	}

	/**
	 * Overloaded Constructor.Instantiates a new invalid input exception.
	 *
	 *
	 * @param message
	 *            : Custom message.
	 * @param operator
	 *            :Operator with Invalid Operand.
	 * @param index
	 *            : Position of Operator.
	 */
	public InvalidInputException(final String message, final String input, final Integer index) {
		super(String.format(message, input, index));
	}

}
