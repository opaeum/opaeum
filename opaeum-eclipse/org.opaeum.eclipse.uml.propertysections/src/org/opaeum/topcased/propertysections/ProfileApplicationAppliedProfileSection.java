package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLPackage;

public class ProfileApplicationAppliedProfileSection extends OpaeumChooserPropertySection{
	ProfileApplication getProfileApplication(){
		return (ProfileApplication) getEObject();
	}
	@Override
	protected Object getFeatureValue(){
		return getProfileApplication().getAppliedProfile();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		TreeIterator<Notifier> allContents = getProfileApplication().eResource().getResourceSet().getAllContents();
		while(allContents.hasNext()){
			Notifier notifier = (Notifier) allContents.next();
			if(notifier instanceof org.eclipse.uml2.uml.Profile){
				result.add(notifier);
			}
		}
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProfileApplication_AppliedProfile();
	}
	@Override
	protected String getLabelText(){
		return "Imported Package";
	}
}
