/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import java.util.List;

import nl.klasse.octopus.model.IOperation;


/**
 * IOperationCallExp : a representation of an expression that refers to an operation on
 * a Classifier.
 */
public interface IOperationCallExp extends IModelPropertyCallExp {
	
	/** 
	 * @return The operation this expression refers to.
	 */
	public abstract IOperation getReferredOperation();
	
	/** 
	 * @return The actual parameters (arguments) that are used in the
	 * operation call that this expression represents.
	 */
	public abstract List<IOclExpression> getArguments();
}