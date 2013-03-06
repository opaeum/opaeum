package org.opaeum.papyrus.uml.diagram.businessprocess.preferences;

import org.eclipse.gmf.runtime.notation.GradientStyle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.activity.part.UMLDiagramEditorPlugin;


public class DiagramPreferenceInitializer extends org.eclipse.papyrus.uml.diagram.activity.preferences.DiagramPreferenceInitializer{
	public DiagramPreferenceInitializer(){
	}
	@Override
	public void initializeDefaultPreferences(){
		IPreferenceStore store = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
		super.initializeDefaultPreferences();
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));
		initOpaqueAction(store);

				//decoration
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.SHADOW), false);
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.ELEMENTICON), false);

	}
	public void initOpaqueAction(IPreferenceStore store){
		String key = ActivityDiagramEditPart.MODEL_ID+ "_OpaqueAction";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(214, 4,53), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
}
