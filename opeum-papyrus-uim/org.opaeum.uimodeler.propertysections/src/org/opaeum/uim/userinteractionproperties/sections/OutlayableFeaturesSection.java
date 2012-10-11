package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.swt.widgets.Label;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.IntegerSubsection;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableFeaturesSection extends AbstractMultiFeaturePropertySection{
	private IntegerSubsection preferredWidth;
	private IntegerSubsection preferredHeight;
	private BooleanSubsection fillHorizontally;
	private BooleanSubsection fillVertically;
	private Label label;
	private Label toLabel;
	public OutlayableFeaturesSection(){
		preferredWidth = createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), "Preferred Width", 133, 100);
		preferredHeight = createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredHeight(), "Preferred Height", 133, 100);
		fillHorizontally = createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), "Fill Horizontally", 133);
		fillVertically = createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillVertically(), "Fill Vertically", 133);
	}
	@Override
	public String getLabelText(){
		return "Layout";
	}
}
