package org.opaeum.uim.userinteractionproperties.binding;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;

public class UimDataTableBindingSection extends AbstractBindingSection{
	public String getLabelText(){
		return "Binding:";
	}
	protected EStructuralFeature getFeature(){
		return ComponentPackage.eINSTANCE.getUimDataTable_Binding();
	}
	protected Object getFeatureValue(){
		return ((UimDataTable) getEObject()).getBinding();
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getTableBinding();
	}
}
