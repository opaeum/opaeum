package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.Orientation;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.userinteractionproperties.core.AbstractEnumerationPropertySection;

public class UimFieldOrientationSection extends AbstractEnumerationPropertySection{
	@Override
	protected String[] getEnumerationFeatureValues(){
		return new String[]{Orientation.HORIZONTAL.getName(),Orientation.VERTICAL.getName()};
	}
	@Override
	protected String getFeatureAsText(){
		Orientation orientation = getUimField().getOrientation();
		return orientation == null ? Orientation.HORIZONTAL.getName() : orientation.getName();
	}
	protected UimField getUimField(){
		return (UimField) getEObject();
	}
	@Override
	protected Object getFeatureValue(String name){
		return Orientation.getByName(name);
	}
	@Override
	protected Object getOldFeatureValue(){
		return getUimField().getOrientation();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_Orientation();
	}
	@Override
	protected String getLabelText(){
		return "Orientation";
	}
}
