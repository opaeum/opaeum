package org.nakeduml.uim.figures;

import org.eclipse.draw2d.StackLayout;
import org.topcased.draw2d.figures.ContainerFigure;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabelFigure;

public class UimTabFigure extends ContainerFigure implements IContainerFigure,ILabelFigure{
	// private ILabel label=new EditableLabel();
	public UimTabFigure(){
		// createContents();
		super.setLayoutManager(new StackLayout());
		getContentPane().setLayoutManager(new org.nakeduml.uim.layouts.StackLayout());
		getLabel().setOpaque(false);
		getLabel().setForegroundColor(getLabel().getBackgroundColor());
		getLabel().setVisible(false);
	}
	// @Override
	// public IFigure getContentPane(){
	// return this;
	// }
	//
	// protected void createContents() {
	// setOpaque(true);
	// }
	// @Override
	// public ILabel getLabel(){
	// label.setBounds(new Rectangle( getBounds().x,getBounds().y,100,20));
	// return label;
	// }
}
