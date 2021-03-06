package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationPropertySection;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.component.UimDataTable;

public class BuiltInLinkKindSection extends AbstractEnumerationPropertySection{
	public String getLabelText(){
		return "Kind:";
	}
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getBuiltInLink_Kind();
	}
	protected String[] getEnumerationFeatureValues(){
		if(getLink().eContainer() instanceof UimDataTable){
			return new String[]{BuiltInLinkKind.AUDIT_TRAIL.getName(),BuiltInLinkKind.BUSINESS_INTELLIGENCE.getName(), BuiltInLinkKind.EDIT.getName(), BuiltInLinkKind.VIEW.getName()};
		}else{
			return new String[]{BuiltInLinkKind.AUDIT_TRAIL.getName(),BuiltInLinkKind.BUSINESS_INTELLIGENCE.getName()};
		}
	}
	protected String getFeatureAsText(){
		return getLink().getKind().getName();
	}
	protected Object getOldFeatureValue(){
		return getLink().getKind();
	}
	private BuiltInLink getLink(){
		return (BuiltInLink) getEObject();
	}
	@Override
	protected Object getFeatureValue(String name){
		return BuiltInLinkKind.getByName(name);
	}
	@Override
	protected String getFeatureAsText(EObject featureOwner){
		return getFeatureAsText();
	}
}