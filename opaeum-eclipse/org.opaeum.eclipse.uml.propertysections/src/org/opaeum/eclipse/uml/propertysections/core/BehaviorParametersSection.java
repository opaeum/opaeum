package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractParametersSection;

public class BehaviorParametersSection extends AbstractParametersSection{
	@Override
	public void refresh(){
		super.refresh();
		Behavior b = (Behavior) getEObject();
		super.setEnabled(b != null && b.getSpecification() == null);
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavior_OwnedParameter();
	}
	public String getLabelText(){
		return null;
	}
}