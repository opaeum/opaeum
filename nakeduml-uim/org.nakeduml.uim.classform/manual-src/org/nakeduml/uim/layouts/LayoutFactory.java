package org.nakeduml.uim.layouts;

import org.eclipse.draw2d.GridLayout;

public class LayoutFactory{
	public static GridLayout createGridLayout(){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 1;
		gridLayout.marginHeight = 1;
		return gridLayout;
	}
}
