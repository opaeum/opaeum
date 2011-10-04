package org.opeum.bpmn2.diagram.figure;

import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.topcased.draw2d.figures.IPortFigure;

public class BoundaryEventFigure extends Ellipse implements IPortFigure{
	@Override
	public Rectangle getBounds(){
		Rectangle bounds2 = super.getBounds();
		if(getUserTaskId() != null){
			for(Figure figure:(List<Figure>) getParent().getChildren()){
				if(figure instanceof UserTaskFigure && getUserTaskId().equals(((UserTaskFigure) figure).getUserTaskId())){
					if(bounds2.y < figure.getBounds().y){
						bounds2.y = figure.getBounds().y;
					}else if( bounds2.y > figure.getBounds().y + figure.getBounds().height){
						bounds2.y = figure.getBounds().y+figure.getBounds().height;
					}
					if(bounds2.x < figure.getBounds().x){
						bounds2.x = figure.getBounds().x;
					}else if( bounds2.x > figure.getBounds().x + figure.getBounds().width){
						bounds2.x = figure.getBounds().x+figure.getBounds().width;
					}
					break;
				}
			}
		}
		return bounds2;
	}
	private String userTaskId;
	private int position;
	@Override
	public int getPosition(){
		return this.position;
	}
	@Override
	public void setPosition(int pos){
		this.position = pos;
	}
	protected void outlineShape(Graphics graphics){
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		graphics.drawOval(r);
	}
	public String getUserTaskId(){
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId){
		this.userTaskId = userTaskId;
	}
}
