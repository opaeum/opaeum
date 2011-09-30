/*
 * Created on Jan 1, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.oclengine.internal;

import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.oclengine.IModelElementReference;


/**
 * ModelElementReferenceImpl : 
 */
public class ModelElementReferenceImpl implements IModelElementReference {
	private IClassifier 	myClass 	= null;
	private IModelElement 	myElement 	= null;
	

	/**
	 * 
	 */
	public ModelElementReferenceImpl(IClassifier cls, IModelElement elem) {
		myClass = cls;
		myElement = elem;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IModelElementReference#getSurroundingClassifier()
	 */
	public IClassifier getSurroundingClassifier() {
		return myClass;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IModelElementReference#getModelElement()
	 */
	public IModelElement getModelElement() {
		return myElement;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IModelElementReference#isInheritedFeature()
	 */

	public boolean isInheritedFeature() {
		boolean inherited = true;
		if (myElement instanceof IOperation) {
			if (((IOperation)myElement).getOwner() == myClass ) 
				inherited = false; 
		}
		if (myElement instanceof IAttribute) {
			if (((IAttribute)myElement).getOwner() == myClass ) 
				inherited = false; 
		}
		if (myElement instanceof IAssociationEnd) {
			if (((IAssociationEnd)myElement).getAssociation().getOtherEnd(((IAssociationEnd)myElement)).getBaseType() == myClass ) 
				inherited = false; 
		}
		return inherited;
	}
	
	public String toString() {
		return myClass.toString() + "::" + myElement.toString();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.facade.IModelElementReference#isTo(nl.klasse.octopus.oclengine.modelinterface.IOperation)
	 */
	public boolean isTo(IOperation newOper) {
		boolean result = false;
		if (newOper.getOwner() == myClass && newOper == myElement) {
			result = true;
		}
		return result;
	}
}
