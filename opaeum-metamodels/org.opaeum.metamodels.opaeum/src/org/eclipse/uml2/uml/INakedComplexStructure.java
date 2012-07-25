package org.eclipse.uml2.uml;

/**
 * Common superclass for all structures in UML that could carry state in multiple properties It includes both message structures as well as
 * structured classifiers
 * 
 */
public interface INakedComplexStructure extends INakedClassifier{
	boolean isPersistent();
}
