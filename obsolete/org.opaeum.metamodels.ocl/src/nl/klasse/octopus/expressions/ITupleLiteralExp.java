/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions;

import java.util.Collection;

/**
 * ITupleLiteralExp : an expression that denotes a tuple value.
 */
public interface ITupleLiteralExp extends ILiteralExp {

	/** 
	 * @return The list of VariableDeclarations that constitute the parts of the
	 * tuple value.
	 */
	public abstract Collection<IVariableDeclaration> getTupleParts();
}