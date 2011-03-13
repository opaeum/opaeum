package org.nakeduml.uim.userinteractionproperties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.util.UimUtil;
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
public class BuiltInActionKindSection extends
		AbstractEnumerationPropertySection {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText() {
		return "Kind:";
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature() {
		return UIMPackage.eINSTANCE.getBuiltInAction_Kind();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getEnumerationFeatureValues()
	 * @generated NOT
	 */
	protected String[] getEnumerationFeatureValues() {
		BuiltInAction action = (BuiltInAction) getEObject();
		UIMForm uf = UimUtil.getNearestForm(action);
		if (uf instanceof OperationInvocationForm) {
			return new String[] { ActionKind.BACK.getName(),
					ActionKind.EXECUTE_OPERATION.getName() };
		} else if (uf instanceof ClassForm || uf instanceof StateForm) {
			return new String[] { ActionKind.BACK.getName(),
					ActionKind.UPDATE.getName(), ActionKind.DELETE.getName() };
		} else if (uf instanceof OperationTaskForm
				|| uf instanceof ActionTaskForm) {
			return new String[] { ActionKind.RETURN_TASK.getName(),
					ActionKind.COMPLETE_TASK.getName(),
					ActionKind.POSTPONE_TASK.getName() };
		} else {
			return new String[0];
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeaturesAsText()
	 * @generated
	 */
	protected String getFeatureAsText() {
		return ((BuiltInAction) getEObject()).getKind().getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(int index) {
		return ActionKind.get(index);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
	 * @generated
	 */
	protected Object getOldFeatureValue() {
		return ((BuiltInAction) getEObject()).getKind();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#isEqual()
	 * @generated
	 */
	protected boolean isEqual(int index) {
		return false;
	}
}