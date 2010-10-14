/*
 * Created on Mar 28, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.oclengine.internal.OclEngine;


/**
 * AssociationClassCall : 
 */
public class AssociationClassCallExp extends NavigationCallExp implements IAssociationClassCallExp {
	private IAssociationClass referredAssociationClass = null;
	/**
	 * 
	 */
	public AssociationClassCallExp() {
		super();
	}

	public AssociationClassCallExp(IAssociationClass referredAssociationClass) {
		this.setReferredAssociationClass(referredAssociationClass);
	}
    
	public IClassifier getNodeType() {
		IClassifier result = null;
		IClassifier endType = null;
		IAssociationEnd end1 = referredAssociationClass.getEnd1();
		IAssociationEnd end2 = referredAssociationClass.getEnd2();
		if (getNavigationSource() == end1) {
			endType = end2.getType();
		} else if (getNavigationSource() == end2) {
			endType = end1.getType();
		} else {
			System.err.println("Cannot find node type of association class call to " + 
								referredAssociationClass.getName());
		}
		if (endType != null && endType.isCollectionKind()) {
			CollectionMetaType metaType = ((ICollectionType)endType).getMetaType();
			result = OclEngine.getCurrentOclLibrary().lookupCollectionType(metaType, referredAssociationClass);
		} else {
			result = referredAssociationClass;  
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressions.IAssociationClassCallExp#getReferredAssociationClass()
	 */
	public IAssociationClass getReferredAssociationClass() {
		return referredAssociationClass;
	}
	public void setReferredAssociationClass(IAssociationClass referredAssociationClass) {
		this.referredAssociationClass = referredAssociationClass;
	}

	public String toString(){
		return "." + referredAssociationClass.getName();
	}

}
