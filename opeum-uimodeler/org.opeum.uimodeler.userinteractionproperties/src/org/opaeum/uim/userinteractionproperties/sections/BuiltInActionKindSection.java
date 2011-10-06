package org.opeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opeum.uim.action.ActionKind;
import org.opeum.uim.action.ActionPackage;
import org.opeum.uim.action.BuiltInAction;
import org.opeum.uim.form.ActionTaskForm;
import org.opeum.uim.form.ClassForm;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.form.OperationInvocationForm;
import org.opeum.uim.form.OperationTaskForm;
import org.opeum.uim.form.StateForm;
import org.opeum.uim.modeleditor.editor.UimEditor;
import org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection;

/**
 * A section which displays the multi features.<br>
 * The section features a table with two buttons letting the user to<br>
 * add or remove items from the selected object.
 *
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class BuiltInActionKindSection extends AbstractEnumerationPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "Kind:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getBuiltInAction_Kind();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getEnumerationFeatureValues()
	 * @generated NOT
	 */
	protected String[] getEnumerationFeatureValues(){
		BuiltInAction action = (BuiltInAction) getEObject();
		FormPanel uf = UimEditor.getCurrentUmlLinks().getNearestForm(action);
		if(uf instanceof OperationInvocationForm){
			return new String[]{
					ActionKind.BACK.getName(),ActionKind.EXECUTE_OPERATION.getName()
			};
		}else if(uf instanceof ClassForm || uf instanceof StateForm){
			return new String[]{
					ActionKind.BACK.getName(),ActionKind.UPDATE.getName(),ActionKind.DELETE.getName()
			};
		}else if(uf instanceof OperationTaskForm || uf instanceof ActionTaskForm){
			return new String[]{
					ActionKind.SUSPEND_TASK.getName(),ActionKind.COMPLETE_TASK.getName(),ActionKind.DELEGATE_TASK.getName()
			};
		}else{
			return new String[0];
		}
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeaturesAsText()
	 * @generated
	 */
	protected String getFeatureAsText(){
		return ((BuiltInAction) getEObject()).getKind().getName();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(int index){
		return ActionKind.get(index);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
	 * @generated
	 */
	protected Object getOldFeatureValue(){
		return ((BuiltInAction) getEObject()).getKind();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#isEqual()
	 * @generated
	 */
	protected boolean isEqual(int index){
		return false;
	}
}