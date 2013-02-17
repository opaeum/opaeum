package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BehavioredClassifierClassifierBehaviorSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior();
	}
	public String getLabelText(){
		return "Classifier Behavior";
	}
	protected Object[] getComboFeatureValues(){
		BehavioredClassifier bc = (BehavioredClassifier) getSelectedObject();
		List<Behavior> result=new ArrayList<Behavior>();
		for(Behavior behavior:bc.getOwnedBehaviors()){
			if(EmfBehaviorUtil.isProcess(behavior)){
				result.add(behavior);
			}
		}
		return (Behavior[]) result.toArray(new Behavior[result.size()]);
	}
	@Override
	protected Command createSingleCommand(EditingDomain editingDomain,Object value,EStructuralFeature f,EObject owner){
		return super.createSingleCommand(editingDomain, value, f, owner);
	}
	protected Object getFeatureValue(){
		return ((BehavioredClassifier) getSelectedObject()).getClassifierBehavior();
	}
}