package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.util.UmlUimLinks;
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
		AbstractEditor uf = UmlUimLinks.getCurrentUmlLinks().getNearestForm(action);
		if(uf instanceof ResponsibilityTaskEditor || uf instanceof ActionTaskEditor){
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