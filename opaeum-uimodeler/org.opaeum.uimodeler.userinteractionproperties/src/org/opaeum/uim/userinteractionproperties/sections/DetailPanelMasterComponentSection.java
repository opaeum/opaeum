package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.uim.form.DetailPanel;
import org.opaeum.uim.form.FormPackage;
import org.opaeum.uim.form.provider.FormItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class DetailPanelMasterComponentSection extends OpaeumChooserPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "MasterComponent:";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return FormPackage.eINSTANCE.getDetailPanel_MasterComponent();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return ((DetailPanel) getEObject()).getMasterComponent();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getComboFeatureValues()
	 * @generated
	 */
	protected Object[] getComboFeatureValues(){
		return getChoices(getEObject(), FormPackage.eINSTANCE.getDetailPanel_MasterComponent().getEType());
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new FormItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
