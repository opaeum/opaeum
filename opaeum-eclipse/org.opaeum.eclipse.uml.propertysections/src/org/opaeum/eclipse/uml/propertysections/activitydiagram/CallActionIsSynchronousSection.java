package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;

public class CallActionIsSynchronousSection extends AbstractBooleanPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getCallAction_IsSynchronous();
	}

	@Override
	protected Boolean getDefaultValue(){
		return true;
	}

	@Override
	public String getLabelText(){
		return "Is Synchronous";
	}
}