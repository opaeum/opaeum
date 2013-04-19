package org.opaeum.bpmn2.diagram.figure;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;

public class BoundaryEventFigure extends IntermediateEventFigure {
	protected enum Position{
		LEFT,
		TOP,
		RIGHT,
		BOTTOM
	}
	private static final int offsetRatio = 2;
	private UserTaskFigure getTaskFigure(){
		UserTaskFigure taskFigure = null;
		if(getUserTaskId() != null){
			for(Figure figure:(List<Figure>) getParent().getChildren()){
				if(figure instanceof UserTaskFigure && getUserTaskId().equals(((UserTaskFigure) figure).getUserTaskId())){
					taskFigure = (UserTaskFigure) figure;
					break;
				}
			}
		}
		return taskFigure;
	}
	public BoundaryEventFigure(){
		super();
	}
	@Override
	public void setBounds(Rectangle bounds2){
		UserTaskFigure figure = getTaskFigure();
		if(figure != null){
			Position position = calcPosition(bounds2, figure);
			Rectangle utfBnds = figure.getBounds();
			switch(position){
			case LEFT:
				bounds2.x = utfBnds.x - (bounds2.width  / offsetRatio);
				calculateY(bounds2, utfBnds);
				break;
			case RIGHT:
				bounds2.x = utfBnds.x + utfBnds.width + (bounds2.width  / offsetRatio) - bounds2.width;
				calculateY(bounds2, utfBnds);
				break;
			case BOTTOM:
				bounds2.y = utfBnds.y - (bounds2.height  / offsetRatio);
				calculateX(bounds2, utfBnds);
				break;
			case TOP:
				bounds2.y = utfBnds.y + utfBnds.height + (bounds2.height  / offsetRatio) - bounds2.height;
				calculateX(bounds2, utfBnds);
				break;
			default:
				break;
			}
		}
		super.setBounds(bounds2);
	}
	private void calculateX(Rectangle bounds2,Rectangle utfBnds){
		if(bounds2.x < utfBnds.x - (bounds2.width  / offsetRatio)){
			bounds2.x = utfBnds.x - (bounds2.width  / offsetRatio);
		}else if(bounds2.x + bounds2.width > utfBnds.x + utfBnds.width + (bounds2.width  / offsetRatio)){
			bounds2.x = utfBnds.x + utfBnds.width + (bounds2.width  / offsetRatio) - bounds2.width;
		}
	}
	private void calculateY(Rectangle bounds2,Rectangle utfBnds){
		if(bounds2.y < utfBnds.y - (bounds2.height  / offsetRatio)){
			bounds2.y = utfBnds.y - (bounds2.height  / offsetRatio);
		}else if(bounds2.y + bounds2.height > utfBnds.y + utfBnds.height + (bounds2.height  / offsetRatio)){
			bounds2.y = utfBnds.y + utfBnds.height + (bounds2.height  / offsetRatio) - bounds2.height;
		}
	}
	private Position calcPosition(Rectangle bounds2,UserTaskFigure utf){
		Rectangle utfBnds = utf.getBounds();
		if(bounds2.x < utfBnds.x - (bounds2.width  / offsetRatio)){
			return Position.LEFT;
		}else if(bounds2.x + bounds2.width > utfBnds.x + utfBnds.width + (bounds2.width  / offsetRatio)){
			return Position.RIGHT;
		}else if(bounds2.y < utfBnds.y - (bounds2.height  / offsetRatio)){
			return Position.BOTTOM;
		}else if(bounds2.y + bounds2.height > utfBnds.y + utfBnds.height + (bounds2.height  / offsetRatio)){
			return Position.TOP;
		}else{
			return Position.LEFT;
		}
	}
}
