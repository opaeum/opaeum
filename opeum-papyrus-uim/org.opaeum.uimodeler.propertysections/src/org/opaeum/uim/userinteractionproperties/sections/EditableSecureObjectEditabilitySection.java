package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;

public class EditableSecureObjectEditabilitySection extends AbstractChooserPropertySection{
	public String getLabelText(){
		return "Editability";
	}
	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getEditableConstrainedObject_Editability();
	}
	protected Object getFeatureValue(){
		return ((EditableConstrainedObject) getEObject()).getEditability();
	}
	protected Object[] getComboFeatureValues(){
		return getChoices(getEObject(), (EClass) ConstraintPackage.eINSTANCE.getEditableConstrainedObject_Editability().getEType());
	}
	protected ILabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		f.add(new UimItemProviderAdapterFactory());
		f.add(new OpaeumItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
}
