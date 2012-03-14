package org.opaeum.uim.userinteractionproperties.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

public class UimFieldBindingSection extends AbstractBindingSection{
	protected String getLabelText(){
		return "Bind to poperty:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_Binding();
	}
	protected Object getFeatureValue(){
		return ((UimField) getEObject()).getBinding();
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getFieldBinding();
	}
}
