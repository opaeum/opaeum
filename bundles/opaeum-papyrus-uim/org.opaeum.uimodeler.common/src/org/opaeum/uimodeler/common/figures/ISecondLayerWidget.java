package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.widgets.Control;

public interface ISecondLayerWidget{
	Control[] getChildren();
	void setVisible(boolean b );
}
