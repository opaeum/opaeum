package org.opaeum.papyrus.uml.diagram.clazz.preferences;

import org.eclipse.gmf.runtime.notation.GradientStyle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;


public class DiagramPreferenceInitializer extends org.eclipse.papyrus.uml.diagram.clazz.preferences.DiagramPreferenceInitializer{
	public DiagramPreferenceInitializer(){
	}
	@Override
	public void initializeDefaultPreferences(){
		IPreferenceStore store = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
		super.initializeDefaultPreferences();
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		PreferenceConverter.setDefault(store, PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));
		initInterface(store);
		initClass(store);
		initSignal(store);
		initComponent(store);
		initPackage(store);
		initEnumeration(store);
		initPrimitiveType(store);
		initDataType(store);

				//decoration
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.SHADOW), false);
		store.setDefault(PreferenceConstantHelper.getPapyrusEditorConstant(PreferenceConstantHelper.ELEMENTICON), false);

	}
	public void initInterface(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Interface";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(220, 220, 220), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initClass(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Class";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(72, 80,189), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initSignal(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Signal";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(158, 72,189), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initPackage(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Package";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(181, 2,23), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initComponent(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Component";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(214, 4,53), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initEnumeration(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_Enumeration";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(214, 4,53), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initDataType(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_DataType";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(214, 4,53), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
	public void initPrimitiveType(IPreferenceStore store){
		String key = ModelEditPart.MODEL_ID + "_PrimitiveType";

		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.GRADIENT_POLICY), true);
		GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(new org.eclipse.swt.graphics.RGB(214, 4,53), new org.eclipse.swt.graphics.RGB(0, 0, 0), GradientStyle.VERTICAL, 50);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());
	}
}
