package org.nakeduml.uim.figures;

import org.nakeduml.uim.layouts.FullLayoutManager;



public class UimFullLayoutFigure extends AbstractLayoutFigure{

	public UimFullLayoutFigure(){
		super();
		setLayoutManager(new FullLayoutManager());
	}
}
