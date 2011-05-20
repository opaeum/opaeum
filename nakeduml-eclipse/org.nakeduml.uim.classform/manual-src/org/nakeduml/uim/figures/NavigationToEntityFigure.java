package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class NavigationToEntityFigure extends Figure implements IBindingFigure{
	private ILabel bindingFigure;
	private ILabel arrow;
	private ILabel end;
	private ILabel toFormFigure;
	public NavigationToEntityFigure(){
		createContents();
	}
	protected void createContents(){
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		LayoutManager layout = gridLayout;
		setLayoutManager(layout);
		add(bindingFigure = new Label(), new GridData());
		add(arrow = new Label("->["), new GridData());
		add(toFormFigure = new Label(), new GridData());
		add(end = new Label("]"), new GridData());
		setBorder(new LineBorder(1));
	}
	public ILabel getArrow(){
		return arrow;
	}
	public ILabel getEnd(){
		return end;
	}
	public ILabel getToFormFigure(){
		return toFormFigure;
	}
	@Override
	public ILabel getBindingLabel(){
		return bindingFigure;
	}
}
