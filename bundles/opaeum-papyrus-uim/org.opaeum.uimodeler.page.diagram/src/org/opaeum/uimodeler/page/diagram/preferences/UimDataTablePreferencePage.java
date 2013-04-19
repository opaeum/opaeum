package org.opaeum.uimodeler.page.diagram.preferences;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;
import org.opaeum.uimodeler.page.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class UimDataTablePreferencePage extends
		AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public static final String compartments[] = { "DataTableColumnCompartment",
			"TableTableActionBarCompartment" };

	/**
	 * @generated
	 */
	public UimDataTablePreferencePage() {
		super();
		setPreferenceKey(PageEditPart.MODEL_ID + "_UimDataTable");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UimDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = PageEditPart.MODEL_ID + "_UimDataTable";
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.WIDTH), 40);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.HEIGHT), 40);

		Map<String, Boolean> map = getStaticCompartmentVisibilityPreferences();
		for (String name : map.keySet()) {
			String preferenceName = PreferenceConstantHelper
					.getLabelElementConstant(key, name,
							PreferenceConstantHelper.COMPARTMENT_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		map = getStaticCompartmentTitleVisibilityPreferences();
		for (String name : map.keySet()) {
			String preferenceName = PreferenceConstantHelper
					.getLabelElementConstant(
							key,
							name,
							PreferenceConstantHelper.COMPARTMENT_NAME_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));

		// Set the default for the gradient
		//store.setDefault(org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.GRADIENT_POLICY),false);
		//org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter gradientPreferenceConverter = new  org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter(
		//		new org.eclipse.swt.graphics.RGB(255, 255, 255),
		//		new org.eclipse.swt.graphics.RGB(0, 0, 0), 0, 0);
		//store.setDefault(org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());

	}

	/**
	 * @generated
	 */
	@Override
	protected void initializeCompartmentsList() {
		for (String name : compartments) {
			this.compartmentsList.add(name);
		}
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		map.put("DataTableColumnCompartment", Boolean.TRUE);
		map.put("TableTableActionBarCompartment", Boolean.TRUE);
		return map;
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentTitleVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		return map;
	}

	/**
	 * @generated
	 */
	protected TreeMap<String, Boolean> getCompartmentTitleVisibilityPreferences() {
		return getStaticCompartmentTitleVisibilityPreferences();
	}

}
