package org.nakeduml.uim.figures;

import org.eclipse.draw2d.XYLayout;

public class UIMXYLayoutFigure extends AbstractLayoutFigure {

	public UIMXYLayoutFigure() {
		super();
		getContentPane().setLayoutManager(new XYLayout());
	}

}
