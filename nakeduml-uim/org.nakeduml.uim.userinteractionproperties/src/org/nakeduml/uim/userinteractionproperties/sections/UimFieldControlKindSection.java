package org.nakeduml.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.UimControl;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.UimUtil;
import org.topcased.tabbedproperties.internal.utils.Messages;
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
public class UimFieldControlKindSection extends AbstractEnumerationPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "ControlKind:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_ControlKind();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getEnumerationFeatureValues()
	 * @generated
	 */
	protected String[] getEnumerationFeatureValues(){
		FieldBinding binding = ((UimField) getEObject()).getBinding();
		ControlKind[] cks = ControlKind.values();
		if(binding != null){
			TypedElement typedElement = UimUtil.getResultingType(binding);
			if(typedElement != null && typedElement.getType() != null){
				cks = ControlUtil.getAllowedControlKinds(UimUtil.getNearestForm(binding.getField()), typedElement);
			}
		}
		String[] result = new String[cks.length];
		for(int i = 0;i < result.length;i++){
			result[i] = cks[i].getName();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeaturesAsText()
	 * @generated
	 */
	protected String getFeatureAsText(){
		return ((UimField) getEObject()).getControlKind().getName();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue()
	 * @generated NOT
	 */
	protected Object getFeatureValue(int index){
		final ControlKind byName = ControlKind.getByName(getEnumerationFeatureValues()[index]);
		return byName;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
	 * @generated
	 */
	protected Object getOldFeatureValue(){
		return ((UimField) getEObject()).getControlKind();
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
	/**
	 * Handle the combo modified event.
	 */
	protected void handleComboModified(){
		final Object oldValue = getOldFeatureValue();
		super.handleComboModified();
		createSetControlCommand(oldValue, getFeatureValue(getCombo().getSelectionIndex()));
	}
	protected void createSetControlCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand(Messages.AbstractTabbedPropertySection_CommandName);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				// TODO transform old control
				UimControl control = createControl((ControlKind) newValue);
				compoundCommand.append(SetCommand.create(editingDomain, nextObject, UimPackage.eINSTANCE.getUimField_Control(), control));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	private UimControl createControl(ControlKind newValue){
		return ControlUtil.instantiate(newValue);
	}
}