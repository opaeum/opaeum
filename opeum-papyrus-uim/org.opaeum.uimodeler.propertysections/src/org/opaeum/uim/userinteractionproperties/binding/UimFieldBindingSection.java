package org.opaeum.uim.userinteractionproperties.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;

public class UimFieldBindingSection extends AbstractBindingSection{
	public String getLabelText(){
		return "Bind to poperty:";
	}
	protected EStructuralFeature getFeature(){
		return ComponentPackage.eINSTANCE.getUimField_Binding();
	}
	protected Object getFeatureValue(){
		return ((UimField) getEObject()).getBinding();
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getFieldBinding();
	}
}
