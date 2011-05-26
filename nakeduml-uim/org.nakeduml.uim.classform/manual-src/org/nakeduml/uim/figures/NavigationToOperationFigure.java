package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;

public class NavigationToOperationFigure extends Figure implements ILabelFigure{
	private ILabel label;
	private ILabel arrow;
	private ILabel toForm;
	private ILabel end;
	public NavigationToOperationFigure(){
		super();
		setLayoutManager(new GridLayout(4, false));
		add(label = new Label());
		add(arrow = new Label("->["));
		add(toForm = new Label());
		add(end = new Label("]"));
	}
	public ILabel getLabel(){
		return label;
	}
	public ILabel getArrow(){
		return arrow;
	}
	public ILabel getToFormFigure(){
		return toForm;
	}
	public ILabel getEnd(){
		return end;
	}
}
