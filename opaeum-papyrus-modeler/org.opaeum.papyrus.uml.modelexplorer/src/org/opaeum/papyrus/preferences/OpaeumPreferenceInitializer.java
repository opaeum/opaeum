package org.opaeum.papyrus.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.gmf.runtime.notation.GradientStyle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.Activator;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;

public class OpaeumPreferenceInitializer extends AbstractPreferenceInitializer{
	private IPreferenceStore store;
	public OpaeumPreferenceInitializer(){
		store = Activator.getDefault().getPreferenceStore();
	}
	@Override
	public void initializeDefaultPreferences(){
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));

		store.setDefault(PreferenceConstantHelper.getElementConstant("Interface", PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(0, 0, 255), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant("Interface", PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());

				//decoration
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.SHADOW), false);
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.ELEMENTICON), false);

	}}
