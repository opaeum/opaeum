/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import nl.klasse.octopus.expressions.internal.types.OclExpression;


/**
 * IIfExp : a representation of an if expression.
 */
public interface IIfExp extends IOclExpression {
	/** Getter for property condition.
	 * @return Value of property condition.
	 */
	public OclExpression getCondition();
	/** Getter for property thenExpression.
	 * @return Value of property thenExpression.
	 */
	public OclExpression getThenExpression();
	/** Getter for property elseExpression.
	 * @return Value of property elseExpression.
	 */
	public OclExpression getElseExpression();
}