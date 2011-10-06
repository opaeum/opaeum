/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;


/**
 * IPropertyCallExp : an expression that refers to a property of a type.
 * A property can be an operation, attribute, association end, predefined 
 * iterator, etc.
 */
public interface IPropertyCallExp extends IOclExpression {
	
	/** 
	 * @return The expression that this property is applied to.
	 * For example, in <code>collection->asSet()<\code> the term 
	 * 'collection' is the source of the property 'asSet'.
	 */
	public abstract IOclExpression getSource();
	
	/**
	 * @return <code>true</code> if the marker '@pre' is attached to this
	 * property call expression
	 */
	public boolean isMarkedPre();
}