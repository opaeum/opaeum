package org.opaeum.metamodel.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class BrokenFeature{
	EObject owner;
	EStructuralFeature feature;
	
	public BrokenFeature(EObject owner,EStructuralFeature feature){
		super();
		this.owner = owner;
		this.feature = feature;
	}
	public EObject getOwner(){
		return owner;
	}
	public EStructuralFeature getFeature(){
		return feature;
	}
	
}
