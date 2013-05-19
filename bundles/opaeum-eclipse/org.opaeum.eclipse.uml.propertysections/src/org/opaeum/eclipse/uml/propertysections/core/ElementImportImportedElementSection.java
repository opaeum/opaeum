package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.emf.extraction.StereotypesHelper;

public class ElementImportImportedElementSection extends AbstractChooserPropertySection{
	ElementImport getElementImport(){
		return (ElementImport) getEObject();
	}
	@Override
	protected Object getFeatureValue(){
		return getElementImport().getImportedElement();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		TreeIterator<Notifier> allContents = getElementImport().eResource().getResourceSet().getAllContents();
		while(allContents.hasNext()){
			Notifier notifier = (Notifier) allContents.next();
			if(notifier instanceof NamedElement && !(notifier instanceof org.eclipse.uml2.uml.Package)){
				NamedElement ne = (NamedElement) notifier;
				if(ne.getModel() != null){
					boolean b = getElementImport().getNearestPackage() instanceof Profile && ne.getModel().getName().equals("UML");
					if(b){
						if(StereotypesHelper.hasStereotype(ne, "Metaclass")){
							b=true;
						}else{
							b=false;
						}
					}
					if(ne.getModel().getAppliedProfile("Ecore") == null || b){
						result.add(notifier);
					}
				}
			}
		}
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getElementImport_ImportedElement();
	}
	@Override
	public String getLabelText(){
		return "Imported Element";
	}
}
