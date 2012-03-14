package org.opaeum.uim.userinteractionproperties.binding;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.BindingPackage;

public class UimDataTableBindingSection extends AbstractBindingSection{
	protected String getLabelText(){
		return "Binding:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimDataTable_Binding();
	}
	protected Object getFeatureValue(){
		return ((UimDataTable) getEObject()).getBinding();
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getTableBinding();
	}
}
