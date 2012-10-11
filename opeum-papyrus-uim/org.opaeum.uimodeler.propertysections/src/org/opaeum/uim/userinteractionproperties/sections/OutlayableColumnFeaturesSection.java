package org.opaeum.uim.userinteractionproperties.sections;

import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.IntegerSubsection;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableColumnFeaturesSection extends AbstractMultiFeaturePropertySection{
	private IntegerSubsection preferredWidth;
	private BooleanSubsection fillHorizontally;
	public OutlayableColumnFeaturesSection(){
		preferredWidth = createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), "Preferred Width", 133, 50);
		fillHorizontally = createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), "Fill Horizontally", 133);
	}
	@Override
	public String getLabelText(){
		return "Layout";
	}
}
