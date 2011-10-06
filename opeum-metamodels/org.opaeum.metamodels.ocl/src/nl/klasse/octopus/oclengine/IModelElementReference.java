/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.oclengine;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;

/**
 * IModelElementReference : because OCL expressions may express initial values etc. of 
 * inherited elements, we must record not only the element itself, but also the subclass
 * in which the expression was encountered. Objects implementing this interface do exactly
 * that. 
 */
public interface IModelElementReference {
	/** 
	 *  If the model element is a classifier, then the model element is returned.
	 *  If the model element is a non-inherited feature, then the owner of this feature
	 *  is returned.
	 *  If the model element is an inherited feature, then the classifier where this 
	 *  feature is refered to, is returned.
	 * @return
	 */
	public IClassifier getSurroundingClassifier();
	
	/** Return the model element that is referenced.
	 * @return
	 */
	public IModelElement getModelElement(); // TODO rename to getFeature()
	
	/** Returns true if 'getModelElement().getOwner()' is not equal to
	 * 'getSurroundingClassifier()'.
	 * @return
	 */
	public boolean isInheritedFeature();
}
