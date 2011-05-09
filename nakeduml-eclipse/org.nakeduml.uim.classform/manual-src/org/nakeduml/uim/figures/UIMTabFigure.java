package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.topcased.draw2d.figures.ContainerFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UIMTabFigure extends ContainerFigure implements IContainerFigure,ILabelFigure{
//	private ILabel label=new EditableLabel();
	public UIMTabFigure() {
//		createContents();
		super.setLayoutManager(new StackLayout());
		getContentPane().setLayoutManager(new org.nakeduml.uim.layouts.StackLayout());
		getLabel().setOpaque(false);
		getLabel().setForegroundColor(getLabel().getBackgroundColor());
		getLabel().setVisible(false);
	}
//	@Override
//	public IFigure getContentPane(){
//		return this;
//	}
//
//	protected void createContents() {
//		setOpaque(true);
//	}
//	@Override
//	public ILabel getLabel(){
//		label.setBounds(new Rectangle( getBounds().x,getBounds().y,100,20));
//		return label;
//	}

}
