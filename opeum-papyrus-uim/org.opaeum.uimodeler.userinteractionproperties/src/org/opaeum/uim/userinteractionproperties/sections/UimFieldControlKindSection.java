package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.userinteractionproperties.core.AbstractEnumerationPropertySection;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public class UimFieldControlKindSection extends AbstractEnumerationPropertySection{
	protected String getLabelText(){
		return "ControlKind:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_ControlKind();
	}
	protected String[] getEnumerationFeatureValues(){
		FieldBinding binding = getUimField().getBinding();
		ControlKind[] cks = ControlKind.values();
		if(binding != null){
			TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks(getUimField()).getResultingType(binding);
			if(typedElement != null && typedElement.getType() != null){
				cks = ControlUtil.getAllowedControlKinds(UmlUimLinks.getNearestForm(binding.getField()),
						typedElement, getUimField().eContainer() instanceof UimDataTable);
			}
		}
		String[] result = new String[cks.length];
		for(int i = 0;i < result.length;i++){
			result[i] = cks[i].getName();
		}
		return result;
	}
	protected String getFeatureAsText(){
		return getUimField().getControlKind().getName();
	}
	private UimField getUimField(){
		return (UimField) getEObject();
	}
	protected Object getOldFeatureValue(){
		return getUimField().getControlKind();
	}
	@Override
	protected Object getFeatureValue(String name){
		return ControlKind.getByName(name);
	}
}