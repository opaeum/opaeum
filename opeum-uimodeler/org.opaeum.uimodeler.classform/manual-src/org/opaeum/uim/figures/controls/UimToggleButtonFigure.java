package org.opeum.uim.figures.controls;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimToggleButtonFigure extends Figure implements IControlFigure{
	private Label bindingLabel;
	public UimToggleButtonFigure(){
		super();
		minSize = new Dimension(10, 10);
		setLayoutManager(new GridLayout(2, false));
		add(new Button("   "), new GridData());
		add(bindingLabel = new Label(), new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false));
	}
	@Override
	public ILabel getBindingLabel(){
		return bindingLabel;
	}
}
