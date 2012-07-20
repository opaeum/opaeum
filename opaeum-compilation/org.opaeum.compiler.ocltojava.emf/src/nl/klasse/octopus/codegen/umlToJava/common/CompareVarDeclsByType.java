/*
 * Created on Feb 5, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.common;

import java.util.Comparator;

import org.eclipse.uml2.uml.Property;

/**
 * CompareVarDeclsByType : 
 */
public class CompareVarDeclsByType implements Comparator<Property> {

	/**
	 * 
	 */
	public CompareVarDeclsByType() { 
		super();
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Property var1, Property var2) {
		if (var1 != null && var2 != null) {
			return var1.getType().getName().compareTo(var2.getType().getName()) ;
		}
		return 0;
	}

}
