package org.opeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opeum.uim.UimField;
import org.opeum.uim.UimPackage;
import org.opeum.uim.binding.BindingPackage;
import org.opeum.uim.binding.UimBinding;
import org.opeum.uim.control.ControlKind;
import org.opeum.uim.modeleditor.editor.UimEditor;
import org.opeum.uim.provider.UimItemProviderAdapterFactory;
import org.opeum.uim.util.ControlUtil;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 * 
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NOT
 */
public class UimFieldBindingSection extends AbstractBindingSection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated NOT
	 */
	protected String getLabelText(){
		return "Binding:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated NOT
	 */
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUimField_Binding();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
	 * @generated NOT
	 */
	protected Object getFeatureValue(){
		return ((UimField) getEObject()).getBinding();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
	 * @generated NOT
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		Object newValue = msg.getNewValue();
		Object oldValue = msg.getOldValue();
		if(newValue instanceof UimBinding && newValue != oldValue){
			switch(msg.getFeatureID(UimField.class)){
			case UimPackage.UIM_FIELD__BINDING:
				ControlKind[] cks = ControlUtil.getAllowedControlKinds(UimEditor.getCurrentUmlLinks().getNearestForm(getEObject()), UimEditor.getCurrentUmlLinks().getResultingType((UimBinding) newValue));
				UimField uimField = (UimField) getEObject();
				if(cks[0] != uimField.getControlKind()){
					uimField.setControlKind(cks[0]);
					uimField.setControl(ControlUtil.instantiate(cks[0]));
				}
			default:
				break;
			}
		}
	}
	@Override
	protected EClass getFeatureEClass(){
		return BindingPackage.eINSTANCE.getFieldBinding();
	}
}
