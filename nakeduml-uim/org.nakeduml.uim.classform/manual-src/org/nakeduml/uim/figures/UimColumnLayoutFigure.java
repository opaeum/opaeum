package org.nakeduml.uim.figures;

import org.nakeduml.uim.layouts.UimColumnLayoutManager;


public class UimColumnLayoutFigure extends AbstractLayoutFigure{

	public UimColumnLayoutFigure(){
		super();
		setLayoutManager(new UimColumnLayoutManager());

	}
}
