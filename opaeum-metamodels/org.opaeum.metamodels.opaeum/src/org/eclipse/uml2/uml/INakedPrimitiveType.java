package org.eclipse.uml2.uml;

import nl.klasse.octopus.model.IPrimitiveType;

/**
 * Classifiers that are ultimately represented as Strings, Reals, Integers and Booleans in OCL/UML Built into the OCL/UML platform. The only
 * operations that these would have are those exposed by the OCL standard library for these primitives
 */
public interface INakedPrimitiveType extends INakedSimpleType{
	String META_CLASS = "primitive";
	/**
	 * Returns true if this is primitive should be represented as one of the four Ocl primitive
	 */
	public boolean isOclPrimitive();
	/**
	 * Returns one of the four Ocl primitive
	 */
	public IPrimitiveType getOclType();
	/**
	 * Returns true if this is primitive should be represented as one of the three Java primitives supported by Octopus: boolean, int or float
	 */
	public void setOclType(IPrimitiveType oclType);
}
