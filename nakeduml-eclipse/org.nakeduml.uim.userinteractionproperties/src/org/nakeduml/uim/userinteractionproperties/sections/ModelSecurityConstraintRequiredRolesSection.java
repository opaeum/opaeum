package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.nakeduml.uim.ModelSecurityConstraint;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.provider.UIMItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;

/**
 * A section which displays the multi features.<br>
 * The section features a table with two buttons letting the user to<br>
 * add or remove items from the selected object.
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ModelSecurityConstraintRequiredRolesSection extends
		AbstractReferencePropertySection {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText() {
		return "RequiredRoles:";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature() {
		return UIMPackage.eINSTANCE.getModelSecurityConstraint_RequiredRoles();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractListPropertySection#getListValues()
	 * @generated
	 */
	protected Object getListValues() {
		return ((ModelSecurityConstraint) getEObject()).getRequiredRoles();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractListPropertySection#getLabelProvider()
	 * @generated
	 */
	protected IBaseLabelProvider getLabelProvider() {
		if (getFeature() instanceof EReference) {
			List f = new ArrayList();
			f.add(new UIMItemProviderAdapterFactory());
			f.addAll(AbstractTabbedPropertySheetPage
					.getPrincipalAdapterFactories());
			return new TabbedPropertiesLabelProvider(
					new ComposedAdapterFactory(f));
		}
		return null;
	}
}