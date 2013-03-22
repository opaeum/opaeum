package org.opaeum.uimodeler.page.diagram.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;
import org.opaeum.uimodeler.page.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class UimFieldPreferencePage extends AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public UimFieldPreferencePage() {
		super();
		setPreferenceKey(PageEditPart.MODEL_ID + "_UimField");
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

		String key = PageEditPart.MODEL_ID + "_UimField";
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.WIDTH), 40);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key,
				PreferenceConstantHelper.HEIGHT), 40);

		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));

		// Set the default for the gradient
		//store.setDefault(org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.GRADIENT_POLICY),false);
		//org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter gradientPreferenceConverter = new  org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter(
		//		new org.eclipse.swt.graphics.RGB(255, 255, 255),
		//		new org.eclipse.swt.graphics.RGB(0, 0, 0), 0, 0);
		//store.setDefault(org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());

	}

}
