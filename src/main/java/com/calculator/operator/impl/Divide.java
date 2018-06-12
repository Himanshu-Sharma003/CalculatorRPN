package com.calculator.operator.impl;


import java.math.BigDecimal;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

/**
 * Implements divide operator.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class Divide implements IOperator {

	/**
	 * BigDecimal Scale.
	 */
	private Integer scale;

	/**
	 * BigDecimal rounding mode.
	 */
	private Integer roundingMode;

	/** Operator String */
	public static final String DIVIDE_STR = "/";

	/**
	 * Register for registering operator
	 */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new divide operator.
	 */
	public Divide() {
		super();
	}

	/**
	 * Instantiates a new divide operator.
	 *
	 * @param notifier
	 *            : EventNotifier.
	 */
	public Divide(final OperatorRegister notifier) {
		this.operatorRegister = notifier;
		this.operatorRegister.register(this);
	}
	

	/**
	 * Overloaded Constructor,instantiates a new divide operator.
	 *
	 * @param scale
	 *            the scale
	 * @param roundingMode
	 *            the rounding mode
	 */
	public Divide(final Integer scale, final Integer roundingMode) {
		this.scale = scale;
		this.roundingMode = roundingMode;
	}


	/**
	 * Overloaded Constructor,instantiates a new divide operator.
	 *
	 * @param notifier
	 *            the notifier
	 * @param scale
	 *            the scale
	 * @param roundingMode
	 *            the rounding mode
	 */
	public Divide(final OperatorRegister notifier, final Integer scale, final Integer roundingMode) {
		this.operatorRegister = notifier;
		this.scale = scale;
		this.roundingMode = roundingMode;
		this.operatorRegister.register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#calculate(com.calculator.commons.RPNStack)
	 */
	@Override
	public void calculate(final RPNStack stack) throws InsufficientParameterException, InvalidInputException {
		if (stack.getStack().size() < 2) {
			throw new InsufficientParameterException(DIVIDE_STR, stack.getIndex());
		} else {
			final BigDecimal firstNumb = stack.getStack().pop();
			final BigDecimal secNumb = stack.getStack().pop();
			try {
				final BigDecimal result = secNumb.divide(firstNumb, scale, roundingMode);
				stack.getStack().push(result);
				stack.getCompleteStack().push(DIVIDE_STR);
				stack.getCompleteStack().push(result.toPlainString());
			} catch (ArithmeticException e) {
				stack.getStack().push(secNumb);
				stack.getStack().push(firstNumb);
				throw new InvalidInputException(
						String.format("Invalid Operation - Infinite Number Position: %d", stack.getIndex()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#getOperator()
	 */
	@Override
	public String getOperator() {
		return DIVIDE_STR;
	}

}
