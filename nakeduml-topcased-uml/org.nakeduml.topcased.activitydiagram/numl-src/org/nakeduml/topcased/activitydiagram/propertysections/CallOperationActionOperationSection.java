package org.nakeduml.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.topcased.propertysections.UmlMetaTypeRemover;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.activitydiagram.commands.update.UpdateCallOperationCommand;
import org.topcased.modeler.uml.activitydiagram.internal.properties.sections.AbstractCallActionSection;
import org.topcased.modeler.uml.activitydiagram.internal.properties.sections.table.AbstractPinTableComposite;
import org.topcased.modeler.uml.activitydiagram.internal.properties.sections.table.CallOperationParameterTableComposite;
import org.topcased.modeler.uml.activitydiagram.utils.CallActionUtils;
import org.topcased.modeler.uml.activitydiagram.utils.CallActionUtils.CallOperationActionOperationUtils;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class CallOperationActionOperationSection extends AbstractCallActionSection<CallOperationAction>{
	@Override
	protected void handleComboModified(){
		if(!isRefreshing()){
			super.createCommand(getFeatureValue(), super.cSingleObjectChooser.getSelection());
		}
		super.handleComboModified();
	}
	@Override
	protected AbstractPinTableComposite<CallOperationAction> createTable(Composite parent){
		return new CallOperationParameterTableComposite(parent, SWT.NONE);
	}
	@Override
	protected String getGroupName(){
		return "Operation";
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
	protected CallOperationAction refreshTableContent(){
		return refreshTable(getEObject() == null ? null : ((CallOperationAction) getEObject()).getOperation());
	}
	private CallOperationAction refreshTable(Operation op){
		CallOperationAction callOp = (CallOperationAction) getEObject();
		return callOp;
	}
	@Override
	protected String getLabelText(){
		return "Operation:";
	}
	@Override
	protected void handleFeatureModified(){
		Operation op = (Operation) getCSingleObjectChooser().getSelection();
		refreshTable(op);
	}
}
