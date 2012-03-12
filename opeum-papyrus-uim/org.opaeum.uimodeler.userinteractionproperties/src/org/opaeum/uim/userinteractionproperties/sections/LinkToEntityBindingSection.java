package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.LinkToEntity;
import org.opaeum.uim.binding.BindingPackage;

public class LinkToEntityBindingSection extends AbstractBindingSection{
	protected String getLabelText(){
		return "Link to:";
	}
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getLinkToEntity_Binding();
	}
	protected Object getFeatureValue(){
		return ((LinkToEntity) getEObject()).getBinding();
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getNavigationBinding();
	}
}
