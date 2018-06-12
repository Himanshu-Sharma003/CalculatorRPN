package com.calculator;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

import com.calculator.cache.CacheManager;
import com.calculator.commons.CalculatorHelper;
import com.calculator.commons.RPNStack;
import com.calculator.commons.exception.InsufficientParameterException;
import com.calculator.commons.exception.InvalidInputException;
import com.calculator.operator.OperatorRegister;
import com.calculator.operator.impl.Divide;
import com.calculator.operator.impl.Minus;
import com.calculator.operator.impl.Multiply;
import com.calculator.operator.impl.Plus;
import com.calculator.operator.impl.SquareRoot;
import com.calculator.operator.impl.Undo;

/**
 *  RPN Calculator Initiator class. RPN Calculator currently implements 
 *  "+","-","/","*","clear","undo" operation. Calculator functionality can be 
 *  extended by providing new Operator implementation and registering the same with
 *  OperatorRegister.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class RPNCalculator {

	/**
	 * RPN Stack.
	 **/
	private static RPNStack stack = new RPNStack();

	/**
	 * Event Register.
	 */
	private static OperatorRegister operatorRegister;

	/**
	 * Decimal Scale for BigDecimal.
	 **/
	private static Integer scale;

	/**
	 * Rounding mode for BigDecimal.
	 */
	private static int roundingMode;

	static {
		scale = Integer.valueOf(CacheManager.get("decimal.place.precision"));
		roundingMode = Integer.valueOf(CacheManager.get("rounding.mode.ROUND_HALF_UP"));
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		// Load logging configuration
		CalculatorHelper.setUp();
		registerOperator();
		CalculatorHelper.log(RPNCalculator.class.getName(), Level.INFO, "main",
				"Please enter operand(s) and operator(s) .......");
		try {
			processInput();
		} catch (InvalidInputException | InsufficientParameterException | OperationNotSupportedException e) {
			CalculatorHelper.log(RPNCalculator.class.getName(), Level.WARNING, "main", e.getMessage());
			CalculatorHelper.log(RPNCalculator.class.getName(), Level.WARNING, "main", stack.toString());
		} catch (Exception e) {
			CalculatorHelper.log(RPNCalculator.class.getName(), Level.SEVERE, "main",
					String.format("Unexpected error occured : %s", e.getMessage()));
		}

	}

	/**
	 * Processes user input by applying operator(s) to operand(s).
	 *
	 * @throws InvalidInputException
	 *             Exception for Invalid Input.
	 * @throws InsufficientParameterException
	 *             Exception for Insufficient Parameters.
	 * @throws OperationNotSupportedException
	 *             Exception for Operation Not Supported.
	 */
	private static void processInput()
			throws InvalidInputException, InsufficientParameterException, OperationNotSupportedException {
		CalculatorHelper.log(RPNCalculator.class.getName(), Level.FINEST, "processInput", "Invoking processInput method");
		final Scanner scanner = new Scanner(System.in);
		Outter: while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			final Scanner lineScanner = new Scanner(line);
			while (lineScanner.hasNext()) {
				final String text = lineScanner.next();
				final Pattern patter = Pattern.compile(CacheManager.get("digit.pattern"));
				final Matcher matcher = patter.matcher(text);
				if (matcher.find()) {
					// Add Operand to stack
					stack.getStack().push(new BigDecimal(text).setScale(scale, roundingMode));
					stack.getCompleteStack().push(text);
				} else if (operatorRegister.getOperatorMap().containsKey(text)) {
					// Invoke Operator
					operatorRegister.getOperator(text).calculate(stack);
				} else {
					switch (text.toUpperCase()) {
					case "CLEAR":
						stack.getStack().clear();
						stack.getCompleteStack().clear();
						break;
					case "QUIT":
					case "EXIT":
						CalculatorHelper.log(RPNCalculator.class.getName(), Level.INFO, "main", "Bye .......");
						break Outter;
					default:
						lineScanner.close();
						lineScanner.close();
						throw new InvalidInputException(text, stack.getIndex());
					}
				}
				stack.setIndex(stack.getIndex() + 2);
			}
			lineScanner.close();
			// Print elements stored in Stack with actual Precision.
			CalculatorHelper.log(RPNCalculator.class.getName(), Level.FINEST, "processInput",
					"Elements Stored in Stack: " + stack.getStack().toString());
			// Prints Stacks after appropriate rounding.
			CalculatorHelper.log(RPNCalculator.class.getName(), Level.INFO, "processInput", String.format(stack.toString()));
			stack.setIndex(1);
		}
		scanner.close();
	}

	/**
	 * Registers operator with OperatorRegister which auto discovery of Operator.
	 */
	private static void registerOperator() {
		operatorRegister = new OperatorRegister();
		// Registering Plus Operator.
		new Plus(operatorRegister);
		// Registering Minus Operator.
		new Minus(operatorRegister);
		// Registering Multiply Operator.
		new Multiply(operatorRegister);
		// Registering Divide Operator.
		new Divide(operatorRegister, scale, roundingMode);
		// Registering Square Root Operator.
		new SquareRoot(operatorRegister, scale, roundingMode);
		// Register Undo Operator
		new Undo(operatorRegister, scale, roundingMode);
		
	}

}
