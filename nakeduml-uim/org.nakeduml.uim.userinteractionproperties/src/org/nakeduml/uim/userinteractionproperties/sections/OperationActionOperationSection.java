package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.nakeduml.eclipse.EmfStateMachineUtil;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.form.StateForm;
import org.nakeduml.uim.provider.UimItemProviderAdapterFactory;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;
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
		return UmlUimLinks.getInstance((UmlReference)getEObject()).getOperation((OperationAction) getEObject());
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
			UimDataTable nearestTable = UimUtil.getNearestTable(oa);
			FormPanel ui = UimUtil.getNearestForm(oa);
			if(nearestTable == null && ui instanceof StateForm){
				// get valid methods for state only
				StateForm sui = (StateForm) ui;
				State state = UmlUimLinks.getInstance((UmlReference)getEObject()).getState(sui);
				if(state != null){
					results.addAll(EmfStateMachineUtil.getTriggerOperations(state));
					results.addAll(EmfStateMachineUtil.getNonTriggerOperations(EmfStateMachineUtil.getStateMachine(state)));
				}
			}else{
				results.addAll(UimUtil.getNearestClass(oa).getAllOperations());
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
