package org.opaeum.uim.userinteractionproperties.sections;

import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.BooleanSubSection;
import org.opaeum.eclipse.uml.propertysections.base.IntegerSubsection;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableColumnFeaturesSection extends AbstractMultiFeaturePropertySection{
	private IntegerSubsection preferredWidth;
	private BooleanSubSection fillHorizontally;
	public OutlayableColumnFeaturesSection(){
		preferredWidth = createInteger(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), "Preferred Width", 133, 30);
		fillHorizontally = createBoolean(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), "Fill Horizontally", 133);
	}
}
