/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import java.util.Collection;

import nl.klasse.octopus.expressions.internal.types.OclExpression;


/**
 * ILoopExp : a representation of an expression that loops over a collection.
 * All elements of the collection are visited and the body is executed for 
 * each of them in turn.
 */
public interface ILoopExp extends IPropertyCallExp {
	/** Getter for property body, i.e. the expression that needs to be executed
	 * for each element of the source collection.
	 * @return The expression that is the body of the loop.
	 */
	public abstract OclExpression getBody();
	/** Getter for property iterators, i.e. the variables that are used to represent
	 * the element of the source collection that is currently being visited.
	 * @return Value of property iterators.
	 */
	public abstract Collection<IVariableDeclaration> getIterators();
}