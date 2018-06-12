package com.calculator.operator.impl;

import java.math.BigDecimal;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

/**
 * Implements Multiply Operator..
 */

/**
 * @author Himanshu Sharma
 *
 */
public class Multiply implements IOperator {

	/**
	 * Multiply Operator String.
	 */
	public static final String MULTIPLY_STR = "*";

	/**
	 * Event Register
	 */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new multiply operator.
	 */
	public Multiply() {
		super();
	}

	/**
	 * Overloaded Constructor ,instantiates a new multiply operator.
	 *
	 * @param notifier
	 *            the notifier
	 */
	public Multiply(final OperatorRegister notifier) {
		this.operatorRegister = notifier;
		this.operatorRegister.register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#calculate(com.calculator.commons.RPNStack)
	 */
	@Override
	public void calculate(final RPNStack stack) throws InsufficientParameterException {
		if (stack.getStack().size() < 2) {
			throw new InsufficientParameterException(MULTIPLY_STR, stack.getIndex());
		} else {
			final BigDecimal firstNumb = stack.getStack().pop();
			final BigDecimal secNumb = stack.getStack().pop();
			final BigDecimal result = secNumb.multiply(firstNumb);
			stack.getStack().push(result);
			stack.getCompleteStack().push(MULTIPLY_STR);
			stack.getCompleteStack().push(result.toPlainString());

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#getOperator()
	 */
	@Override
	public String getOperator() {
		return MULTIPLY_STR;
	}

}
