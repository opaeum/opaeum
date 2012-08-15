/*
 * Created on Feb 5, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.common;

import java.util.Comparator;

import nl.klasse.octopus.expressions.IVariableDeclaration;

/**
 * CompareVarDeclsByType : 
 */
public class CompareVarDeclsByType implements Comparator<IVariableDeclaration> {

	/**
	 * 
	 */
	public CompareVarDeclsByType() { 
		super();
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(IVariableDeclaration var1, IVariableDeclaration var2) {
		if (var1 != null && var2 != null) {
			return var1.getType().getName().compareTo(var2.getType().getName()) ;
		}
		return 0;
	}

}
