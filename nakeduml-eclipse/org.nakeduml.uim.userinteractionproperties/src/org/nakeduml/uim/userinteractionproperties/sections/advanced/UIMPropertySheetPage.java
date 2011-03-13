package org.nakeduml.uim.userinteractionproperties.sections.advanced;

import java.util.ArrayList;
import java.util.List;


import org.nakeduml.uim.provider.UIMItemProviderAdapterFactory;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.properties.ModelerPropertySheetPage;

/**
 *  A property sheet page that provides a tabbed UI.
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class UIMPropertySheetPage extends ModelerPropertySheetPage {
	/**
	 * Create a new tabbed property sheet page.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param editor The editor contributor of the property sheet page.
	 * @generated
	 */
	public UIMPropertySheetPage(Modeler editor) {
		super(editor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage#getAdapterFactories()
	 * @generated NOT
	 */
	public List getAdapterFactories() {
		List factories = new ArrayList();
		factories.add(new UIMItemProviderAdapterFactory());
		factories.addAll(super.getAdapterFactories());

		return factories;
	}
}
