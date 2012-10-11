package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationPropertySection;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.panel.Orientation;

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
		return ComponentPackage.eINSTANCE.getUimField_Orientation();
	}
	@Override
	public String getLabelText(){
		return "Orientation";
	}
}
