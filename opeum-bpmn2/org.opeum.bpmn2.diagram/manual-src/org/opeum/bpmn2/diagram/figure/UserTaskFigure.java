package org.opeum.bpmn2.diagram.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.layout.BorderAttachedLayout;
import org.topcased.draw2d.layout.CenterLayout;

public class UserTaskFigure extends Figure implements IContainerFigure,ILabelFigure,HandleBounds{
	private ILabel label;
	private IFigure contentPane;
	private IFigure innerRectangle;
	private String userTaskId;
	public UserTaskFigure(){
		LayoutManager layout = new StackLayout();
		setLayoutManager(layout);
		IFigure outerRectangle = new Figure();
		outerRectangle.setLayoutManager(new StackLayout());
		outerRectangle.setBorder(new MarginBorder(10));
		outerRectangle.setOpaque(false);
		innerRectangle = createBackgroundFigure();
		innerRectangle.setLayoutManager(new CenterLayout());
		innerRectangle.setOpaque(true);
		outerRectangle.add(innerRectangle);
		EditableLabel nameLbl = new EditableLabel(true);
		nameLbl.setTextJustification(PositionConstants.CENTER);
		label = new ComposedLabel(new Label(), nameLbl, null, false);
		label.setOpaque(false);
		innerRectangle.add(label);
		add(outerRectangle);
		contentPane = new Figure();
		contentPane.setOpaque(false);
		contentPane.setLayoutManager(new BorderAttachedLayout());
		add(contentPane);
	}
	protected IFigure createBackgroundFigure(){
		return new RoundedRectangle();
	}
	public IFigure getBackgroundFigure(){
		return innerRectangle;
	}
	public ILabel getLabel(){
		return label;
	}
	public IFigure getContentPane(){
		return contentPane;
	}
	public Rectangle getHandleBounds(){
		return getBounds();
	}
	public IFigure getToolTip(){
		return getLabel().getToolTip();
	}
	public String getUserTaskId(){
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId){
		this.userTaskId = userTaskId;
	}
}
