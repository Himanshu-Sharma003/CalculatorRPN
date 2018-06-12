package com.calculator.operator.impl;

import java.math.BigDecimal;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

/**
 *Implements Plus Operation
 */

/**
 * @author Himanshu Sharma
 *
 */
public class Plus implements IOperator {

	/**
	 * Plus Operator String.
	 */
	public static final String PLUS_STR = "+";

	/**
	 * Event Register
	 */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new plus operator.
	 */
	public Plus() {
		super();
	}

	/**
	 * Overloaded Constructor, instantiates a new plus operator.
	 *
	 * @param notifier
	 *            the notifier
	 */
	public Plus(final OperatorRegister notifier) {
		this.operatorRegister = notifier;
		this.operatorRegister.register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#getOperator()
	 */
	@Override
	public String getOperator() {
		return PLUS_STR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#calculate(com.calculator.commons.RPNStack)
	 */
	@Override
	public void calculate(final RPNStack stack) throws InsufficientParameterException {
		if (stack.getStack().size() < 2) {
			throw new InsufficientParameterException(PLUS_STR, stack.getIndex());
		} else {
			final BigDecimal result = stack.getStack().pop().add(stack.getStack().pop());
			stack.getStack().push(result);
			stack.getCompleteStack().push(PLUS_STR);
			stack.getCompleteStack().push(result.toPlainString());

		}

	}

}
