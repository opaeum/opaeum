package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.swt.widgets.Label;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.BooleanSubSection;
import org.opaeum.eclipse.uml.propertysections.base.IntegerSubsection;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableFeaturesSection extends AbstractMultiFeaturePropertySection{
	private IntegerSubsection preferredWidth;
	private IntegerSubsection preferredHeight;
	private BooleanSubSection fillHorizontally;
	private BooleanSubSection fillVertically;
	private Label label;
	private Label toLabel;
	public OutlayableFeaturesSection(){
		preferredWidth=createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), "Preferred Width", 133, 30);
		preferredHeight=createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredHeight(), "Preferred Height", 133, 30);
		fillHorizontally=createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), "Fill Horizontally", 133);
		fillVertically=createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillVertically(), "Fill Vertically", 133);
		
	}
}
