package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaEventListener;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;

public class ElementImportImportedElementSection extends OpaeumChooserPropertySection{
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
					if(ne.getModel().getAppliedProfile("Ecore") == null){
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
	protected String getLabelText(){
		return "Imported Element";
	}
}
