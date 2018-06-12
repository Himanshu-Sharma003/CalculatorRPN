package com.calculator.commons;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Class providing Stack implementation for Calculator.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class RPNStack {

	/**
	 * Index/Position of Operand or Operator
	 */
	private Integer index;

	/**
	 * This stack contains only operands.
	 */
	private Stack<BigDecimal> stack;

	/**
	 * This stack contains operator and operand.
	 */
	private Stack<String> completeStack;

	/**
	 * Instantiates a new RPN stack.
	 */
	public RPNStack() {
		super();
		this.stack = new Stack<>();
		this.completeStack = new Stack<>();
		this.index = Integer.valueOf(1);
	}

	/**
	 * Gets the stack.
	 *
	 * @return the stack
	 */
	public Stack<BigDecimal> getStack() {
		return stack;
	}

	/**
	 * Sets the stack.
	 *
	 * @param rpnStack
	 *            the new stack
	 */
	public void setStack(final Stack<BigDecimal> rpnStack) {
		this.stack = rpnStack;
	}

	/**
	 * Gets the position/Index of Input.
	 *
	 * @return the position/Index of Input
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the position/Index of Input.
	 *
	 * @param index
	 *            the new position/Index of Input
	 */
	public void setIndex(final Integer index) {
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("stack: ");
		for (final BigDecimal bigDecimal : stack) {
			if (bigDecimal.doubleValue() == 0) {
				builder.append("0");
			} else {
				String str = bigDecimal.setScale(10, 1).toPlainString();
				str = str.indexOf(".") < 0 ? str : str.replaceAll("0*$", "").replaceAll("\\.$", "");
				builder.append(str);
			}
			builder.append(" ");
		}
		return builder.toString();
	}

	/**
	 * @return the completeStack
	 */
	public Stack<String> getCompleteStack() {
		return completeStack;
	}

	/**
	 * @param completeStack
	 *            the completeStack to set
	 */
	public void setCompleteStack(Stack<String> completeStack) {
		this.completeStack = completeStack;
	}

}
