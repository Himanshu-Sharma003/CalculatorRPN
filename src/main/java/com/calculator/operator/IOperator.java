package com.calculator.operator;

import javax.naming.OperationNotSupportedException;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;

/**
 * Define methods to be implemented by concrete operator class.
 */

/**
 * @author Himanshu Sharma
 *
 */
public interface IOperator {

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	String getOperator();

	/**
	 * Calculate result after applying the operator on operand(s).
	 *
	 * @param stack
	 *            the stack
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 *             the invalid input exception
	 * @throws OperationNotSupportedException
	 *             the operation not supported exception
	 */
	void calculate(RPNStack stack)
			throws InsufficientParameterException, InvalidInputException, OperationNotSupportedException;

}
