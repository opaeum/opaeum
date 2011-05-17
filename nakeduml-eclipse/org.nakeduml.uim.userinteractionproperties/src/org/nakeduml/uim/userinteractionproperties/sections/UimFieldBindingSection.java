package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.UimBinding;
import org.nakeduml.uim.UimControl;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.modeleditor.SafeUmlUimLinks;
import org.nakeduml.uim.provider.UimItemProviderAdapterFactory;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

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
				ControlKind[] cks = ControlUtil.getAllowedControlKinds(UimUtil.getNearestForm(getEObject()), UimUtil.getResultingType((UimBinding) newValue));
				UimField uimField = (UimField)getEObject();
				if(cks[0]!=uimField.getControlKind()){
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
		return UimPackage.eINSTANCE.getFieldBinding();
	}

}
