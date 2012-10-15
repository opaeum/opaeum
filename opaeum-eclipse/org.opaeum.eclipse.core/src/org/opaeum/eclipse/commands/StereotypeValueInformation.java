package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ProfileApplier;

public class StereotypeValueInformation{
	private String profileName;
	private String stereotypeName;
	private String featureName;
	private boolean isCompositeFeature;
	public StereotypeValueInformation(String profileName,String stereotypeName,String featureName,boolean isCompositeFeature){
		this.profileName = profileName;
		this.stereotypeName = stereotypeName;
		this.featureName = featureName;
		this.isCompositeFeature=isCompositeFeature;
	}
	public StereotypeValueInformation(String profileName,String stereotypeName,String featureName){
		this(profileName,stereotypeName,featureName,true);
	}
	public String getProfileName(){
		return profileName;
	}
	public String getStereotypeName(){
		return stereotypeName;
	}
	public String getFeatureName(){
		return featureName;
	}
	public boolean isCompositeFeature(){
		return isCompositeFeature;
	}
	public EClassifier getExpectedType(EObject source){
		EStructuralFeature sf = getFeature(source);
		return sf.getEType();
	}
	public EStructuralFeature getFeature(EObject source){
		Profile p= ProfileApplier.getProfile(source, profileName);
		EClass clss = (EClass) p.getDefinition().getEClassifier(stereotypeName);
		EStructuralFeature sf = clss.getEStructuralFeature(featureName);
		return sf;
	}
	

}
