package com.calculator.operator.impl;

import java.math.BigDecimal;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

/**
 * Implements Minus Operation.
 */
/**
 * @author Himanshu Sharma
 *
 */
public class Minus implements IOperator {

	/**
	 * Minus Operator String
	 */
	public static final String MINUS_STR = "-";

	/**
	 * Event Register
	 */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new minus operator.
	 */
	public Minus() {
		super();
	}

	/**
	 * Overloaded Constructor ,instantiates a new minus operator.
	 *
	 * @param notifier
	 *            the notifier
	 */
	public Minus(final OperatorRegister notifier) {
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
			throw new InsufficientParameterException(MINUS_STR, stack.getIndex());
		} else {
			final BigDecimal firstNumb = stack.getStack().pop();
			final BigDecimal secNumb = stack.getStack().pop();
			stack.getStack().push(secNumb.subtract(firstNumb));
			stack.getCompleteStack().push(MINUS_STR);
			stack.getCompleteStack().push(secNumb.subtract(firstNumb).toPlainString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#getOperator()
	 */
	@Override
	public String getOperator() {
		return MINUS_STR;
	}
}
