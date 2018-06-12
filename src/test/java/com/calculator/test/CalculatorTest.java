package com.calculator.test;


import java.math.BigDecimal;

import javax.naming.OperationNotSupportedException;

import com.calculator.commons.RPNStack;
import com.calculator.commons.Test;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;
import com.calculator.operator.IOperator;
import com.calculator.operator.OperatorRegister;
import com.calculator.operator.impl.Divide;
import com.calculator.operator.impl.Minus;
import com.calculator.operator.impl.Multiply;
import com.calculator.operator.impl.Plus;
import com.calculator.operator.impl.SquareRoot;
import com.calculator.operator.impl.Undo;

/**
 * The Class CalculatorTest.
 */
/**
 * @author Himanshu Sharma
 *
 */
public class CalculatorTest {

	/**
	 * Test add.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test
	public void testAdd() throws InsufficientParameterException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		rpnStack.getStack().add(new BigDecimal(12));
		rpnStack.getStack().add(new BigDecimal(20));
		rpnStack.getStack().add(new BigDecimal(30));
		final Plus operator = new Plus(new OperatorRegister());
		// Add
		operator.calculate(rpnStack);
		// Add
		operator.calculate(rpnStack);
		// Add
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(63)) != 0) {
			throw new AssertionError();
		}
	}

	/**
	 * Test add insuff opr.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test
	public void testAddInsuffOpr() {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		final Plus operator = new Plus(new OperatorRegister());
		// Add
		try {
			operator.calculate(rpnStack);
		} catch (InsufficientParameterException e) {
			// Expected result
		}

	}

	/**
	 * Test minus.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test
	public void testMinus() throws InsufficientParameterException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		rpnStack.getStack().add(new BigDecimal(12));
		rpnStack.getStack().add(new BigDecimal(20));
		rpnStack.getStack().add(new BigDecimal(30));
		final Minus operator = new Minus(new OperatorRegister());
		// Minus
		operator.calculate(rpnStack);
		// Minus
		operator.calculate(rpnStack);
		// Minus
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(-21)) != 0) {
			throw new AssertionError();
		}
	}

	/**
	 * Test minus insuff opr.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test(enabled = false)
	public void testMinusInsuffOpr() throws InsufficientParameterException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		final Minus operator = new Minus(new OperatorRegister());
		// Minus
		operator.calculate(rpnStack);
	}

	/**
	 * Test multiply.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test
	public void testMultiply() throws InsufficientParameterException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		rpnStack.getStack().add(new BigDecimal(12));
		rpnStack.getStack().add(new BigDecimal(20));
		rpnStack.getStack().add(new BigDecimal(30));
		final Multiply operator = new Multiply(new OperatorRegister());
		// Multiply
		operator.calculate(rpnStack);
		// Multiply
		operator.calculate(rpnStack);
		// Multiply
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(7200)) != 0) {
			throw new AssertionError();
		}
	}

	/**
	 * Test multiply insuff opr.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 */
	@Test(enabled = false)
	public void testMultiplyInsuffOpr() throws InsufficientParameterException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		final Multiply operator = new Multiply(new OperatorRegister());
		// Multiply
		operator.calculate(rpnStack);
	}

	/**
	 * Test divide.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	@Test
	public void testDivide() throws InsufficientParameterException, InvalidInputException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		rpnStack.getStack().add(new BigDecimal(2));
		final Divide operator = new Divide(new OperatorRegister(),20, 4);
		// Divide
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(.5).setScale(20, 4)) != 0) {
			throw new AssertionError();
		}
	}

	/**
	 * Test divide insuff opr.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	@Test(enabled = false)
	public void testDivideInsuffOpr() throws InsufficientParameterException, InvalidInputException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(BigDecimal.ONE);
		final Divide operator = new Divide(new OperatorRegister());
		// Divide
		operator.calculate(rpnStack);
	}

	/**
	 * Test sqrt.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 *             the invalid input exception
	 */
	@Test
	public void testSqrt() throws InsufficientParameterException, InvalidInputException {
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(new BigDecimal(2));
		final SquareRoot operator = new SquareRoot(new OperatorRegister(),20, 4);
		// Square Root
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(Math.sqrt(2)).setScale(20, 4)) != 0) {
			throw new AssertionError();
		}
	}

	@Test
	public void testAll() throws InsufficientParameterException, InvalidInputException, OperationNotSupportedException {
		final OperatorRegister register = new OperatorRegister();
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(new BigDecimal(4));
		rpnStack.getStack().add(new BigDecimal("32.125"));
		rpnStack.getStack().add(new BigDecimal(13));
		IOperator operator = new Minus(register);
		// Minus
		operator.calculate(rpnStack);

		operator = new Multiply(register);
		// Multiply
		operator.calculate(rpnStack);

		rpnStack.getStack().add(new BigDecimal(20));
		// Plus
		operator = new Plus(register);
		operator.calculate(rpnStack);

		operator = new SquareRoot(register,20, 4);
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().compareTo(new BigDecimal(Math.sqrt(96.5)).setScale(20, 4)) != 0) {
			throw new AssertionError("Result Not as expected.");
		}
	}

	/**
	 * Test precision.
	 *
	 * @throws InsufficientParameterException
	 *             the insufficient parameter exception
	 * @throws InvalidInputException
	 *             the invalid input exception
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public void testPrecision() throws InsufficientParameterException, InvalidInputException, OperationNotSupportedException {

		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().add(new BigDecimal(2));
		IOperator operator = new SquareRoot(new OperatorRegister(),20, 4);
		operator.calculate(rpnStack);

		if (rpnStack.getStack().pop().precision() < 16) {
			throw new AssertionError("Precision stored is less than 15 decimal points.");
		}
	}
	
	@Test
	public void testUndo() throws InsufficientParameterException, InvalidInputException, OperationNotSupportedException {
		final OperatorRegister register = new OperatorRegister();
		final RPNStack rpnStack = new RPNStack();
		rpnStack.getStack().push(new BigDecimal(5));
		rpnStack.getCompleteStack().push(new BigDecimal(5).toPlainString());
		
		rpnStack.getStack().push(new BigDecimal(4));
		rpnStack.getCompleteStack().push(new BigDecimal(4).toPlainString());
		
		
		IOperator operator = new Multiply(register);
		operator.calculate(rpnStack);
		 if(rpnStack.getStack().lastElement().doubleValue() != 20 ){
			 throw new AssertionError("Invalid REsult");
		 }
		 
		 operator = new Undo(register,20, 4);
		 operator.calculate(rpnStack);
		 
		 if(rpnStack.getStack().lastElement().doubleValue() != 5 ){
			 throw new AssertionError("Invalid REsult");
		 }
	}
}
	
	