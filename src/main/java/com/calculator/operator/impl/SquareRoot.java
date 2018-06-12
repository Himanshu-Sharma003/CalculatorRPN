package com.calculator.operator.impl;


import java.math.BigDecimal;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

/**
 * Implements Square root Operator.
 */
/**
 * @author Himanshu Sharma
 *
 */
public class SquareRoot implements IOperator {

	/**
	 * BigDecimal Scale.
	 */
	private Integer scale;

	/**
	 * BigDecimal rounding mode.
	 */
	private Integer roundingMode;

	/**
	 * Square Root Operator String
	 */
	public static final String SQUARE_ROOT_STR = "sqrt";

	/**
	 * Event Register.
	 */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new square root operator.
	 */
	public SquareRoot() {
		super();
	}

	/**
	 * Overloaded Constructor, instantiates a new square root operator.
	 *
	 * @param notifier
	 *            the notifier
	 */
	public SquareRoot(final OperatorRegister notifier) {
		this.operatorRegister = notifier;
		this.operatorRegister.register(this);
	}

	/**
	 * Overloaded Constructor, instantiates a new square root operator.
	 *
	 * @param notifier
	 *            the notifier
	 * @param scale
	 *            the scale
	 * @param roundingMode
	 *            the rounding mode
	 */
	public SquareRoot(final OperatorRegister notifier, final Integer scale, final Integer roundingMode) {
		this.operatorRegister = notifier;
		this.scale = scale;
		this.roundingMode = roundingMode;
		this.operatorRegister.register(this);
	}

	/**
	 * Instantiates a new square root operator.
	 *
	 * @param scale
	 *            the scale
	 * @param roundingMode
	 *            the rounding mode
	 */
	public SquareRoot(final Integer scale, final Integer roundingMode) {
		this.scale = scale;
		this.roundingMode = roundingMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#calculate(com.calculator.commons.RPNStack)
	 */
	@Override
	public void calculate(final RPNStack stack) throws InsufficientParameterException, InvalidInputException {
		if (stack.getStack().size() < 1) {
			throw new InsufficientParameterException(SQUARE_ROOT_STR, stack.getIndex());
		} else {
			final BigDecimal number = stack.getStack().pop();
			if (number.compareTo(BigDecimal.ZERO) < 0) {
				String str = number.toString();
				str = str.indexOf(".") < 0 ? str : str.replaceAll("0*$", "").replaceAll("\\.$", "");
				stack.getStack().push(number);
				throw new InvalidInputException("Invalid Number : %s  Position: %d", str, stack.getIndex());
			} else if (number.compareTo(BigDecimal.ZERO) == 0) {
				stack.getStack().push(BigDecimal.ZERO);
				stack.getCompleteStack().push(SQUARE_ROOT_STR);
				stack.getCompleteStack().push(BigDecimal.ZERO.toPlainString());

			} else {
				final BigDecimal result = new BigDecimal(Math.sqrt(number.doubleValue())).setScale(scale, roundingMode);
				stack.getStack().push(result);
				stack.getCompleteStack().push(SQUARE_ROOT_STR);
				stack.getCompleteStack().push(result.toPlainString());
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
		return SQUARE_ROOT_STR;
	}

}
