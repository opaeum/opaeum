package org.opeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.opeum.eclipse.EmfStateMachineUtil;
import org.opeum.uim.UimDataTable;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.form.StateForm;
import org.opeum.uim.modeleditor.editor.UimEditor;
import org.opeum.uim.provider.UimItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 *
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class OperationActionOperationSection extends AbstractChooserPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "Operation:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return null;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return UimEditor.getCurrentUmlLinks().getOperation((OperationAction) getEObject());
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		Collection<Operation> results = new ArrayList<Operation>();
		if(getEObject() instanceof OperationAction){
			OperationAction oa = (OperationAction) getEObject();
			UimDataTable nearestTable = UimEditor.getCurrentUmlLinks().getNearestTable(oa);
			FormPanel ui = UimEditor.getCurrentUmlLinks().getNearestForm(oa);
			if(nearestTable == null && ui instanceof StateForm){
				// get valid methods for state only
				StateForm sui = (StateForm) ui;
				State state = UimEditor.getCurrentUmlLinks().getState(sui);
				if(state != null){
					results.addAll(EmfStateMachineUtil.getTriggerOperations(state));
					results.addAll(EmfStateMachineUtil.getNonTriggerOperations(EmfStateMachineUtil.getStateMachine(state)));
				}
			}else{
				results.addAll(UimEditor.getCurrentUmlLinks().getNearestClass(oa).getAllOperations());
			}
		}
		return getParameterlessOperations(results);
	}
	private Object[] getParameterlessOperations(Collection<Operation> opers){
		List<Operation> results = new ArrayList<Operation>();
		for(Operation operation:opers){
			if(operation.getOwnedParameters().isEmpty()){
				results.add(operation);
			}
		}
		return (Operation[]) results.toArray(new Operation[results.size()]);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
