package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.UmlMetaTypeRemover;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class CallOperationActionOperationSection extends AbstractChooserPropertySection{
	@Override
	protected void handleComboModified(){
		super.handleComboModified();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getOperation());
		choices.addAll(UmlMetaTypeRemover.removeAll(types));
		return choices.toArray();
	}
	@Override
	protected Object getFeatureValue(){
		return ((CallOperationAction) getEObject()).getOperation();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getCallOperationAction_Operation();
	}
	@Override
	protected String getLabelText(){
		return "Operation:";
	}
}
