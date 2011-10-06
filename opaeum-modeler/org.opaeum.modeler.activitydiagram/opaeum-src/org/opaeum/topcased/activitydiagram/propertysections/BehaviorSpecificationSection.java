package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public class BehaviorSpecificationSection extends AbstractChooserPropertySection{
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		choices.addAll(EmfBehaviorUtil.findSpecificationsInScope(getBehavior()));
		return choices.toArray();
	}
	private Behavior getBehavior(){
		return (Behavior) getEObject();
	}
	@Override
	protected Object getFeatureValue(){
		return getBehavior().getSpecification();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavior_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Contract:";
	}
	@Override
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
}
