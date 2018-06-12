package com.calculator.operator;

import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

/**
 * Registers Operation. Contains Map with key as Operator Symbol and
 * values as Implementation.
 */

/**
 * @author Himanshu Sharma
 *
 */
public class OperatorRegister {

	/**
	 * Map holds Operator as Key and Implementation as Value
	 **/
	private Map<String, IOperator> operatorMap;

	/**
	 * Instantiates a new event notifier.
	 */
	public OperatorRegister() {
		super();
		operatorMap = new HashMap<>();
	}

	/**
	 * Register new Operator by loading into the Map.
	 *
	 * @param operator
	 *            Operator.
	 */
	public void register(final IOperator operator) {
		this.operatorMap.put(operator.getOperator(), operator);
	}

	/**
	 * Gets the operator map.
	 *
	 * @return the operatorMap
	 */
	public Map<String, IOperator> getOperatorMap() {
		return operatorMap;
	}

	/**
	 * Sets the operator map.
	 *
	 * @param operatorMap
	 *            the operatorMap to set
	 */
	public void setOperatorMap(final Map<String, IOperator> operatorMap) {
		this.operatorMap = operatorMap;
	}

	/**
	 * Return Operator Implementation for operator type(String).
	 *
	 * @param operatorStr
	 *            : Operator String
	 * @return the operator
	 * @throws OperationNotSupportedException
	 */
	public IOperator getOperator(final String operatorStr) throws OperationNotSupportedException {
		final IOperator operator = operatorMap.get(operatorStr);
		if (operator == null) {
			throw new OperationNotSupportedException("Operation Not Implemented");
		}
		return operator;
	}
}
