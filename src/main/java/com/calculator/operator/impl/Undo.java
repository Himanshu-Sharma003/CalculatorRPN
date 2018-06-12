package com.calculator.operator.impl;

import java.math.BigDecimal;
import java.util.Stack;

import javax.naming.OperationNotSupportedException;

import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;

// TODO: Auto-generated Javadoc
/**
 * Implements Undo operator.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class Undo implements IOperator {

	/**
	 * Undo Operator String.
	 */
	public static final String UNDO_STR = "undo";

	/**
	 * BigDecimal Scale.
	 */
	private Integer scale;

	/**
	 * BigDecimal rounding mode.
	 */
	private Integer roundingMode;

	/** Event Register. */
	private OperatorRegister operatorRegister;

	/**
	 * Instantiates a new undo operator.
	 */
	public Undo() {
		super();
	}

	/**
	 * Overloaded Constructor, instantiates a new undo operator.
	 *
	 * @param notifier
	 *            the notifier
	 */
	public Undo(final OperatorRegister notifier) {
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
	public Undo(final Integer scale, final Integer roundingMode) {
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
	public Undo(final OperatorRegister notifier, final Integer scale, final Integer roundingMode) {
		this.operatorRegister = notifier;
		this.scale = scale;
		this.roundingMode = roundingMode;
		this.operatorRegister.register(this);
	}

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#getOperator()
	 */
	@Override
	public String getOperator() {
		return UNDO_STR;
	}

	/**
	 * Calculate.
	 *
	 * @param stack
	 *            the stack
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 * @throws OperationNotSupportedException
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.calculator.operator.IOperator#calculate(com.calculator.commons.RPNStack)
	 */
	@Override
	public void calculate(final RPNStack stack)
			throws InsufficientParameterException, OperationNotSupportedException, InvalidInputException {
		final Stack<String> completeStack = stack.getCompleteStack();
		final int size = completeStack.size();
		if (size <= 1) {
			throw new InsufficientParameterException(UNDO_STR, size);
		}
		// If top 2 elements are number , then simple remove .
		stack.getStack().pop();
		completeStack.pop();
		if (operatorRegister.getOperatorMap().containsKey(completeStack.lastElement())) {
			String lastElement = "";
			if (!SquareRoot.SQUARE_ROOT_STR.equalsIgnoreCase(completeStack.pop())) {
				lastElement = completeStack.pop();
			}
			stack.getStack().push(new BigDecimal(completeStack.pop()).setScale(scale, roundingMode));
			if(!lastElement.isEmpty())
				stack.getStack().push(new BigDecimal(lastElement).setScale(scale, roundingMode));
		}
	}

}
