package org.nakeduml.uim.figures.controls;


import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.nakeduml.uim.figures.IControlFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UIMCheckBoxFigure extends Figure implements IControlFigure {
	private CheckBox checkBox;
	private Label bindingLabel;

	public UIMCheckBoxFigure() {
		super();
		setLayoutManager(new GridLayout(2, false));
		add(checkBox = new CheckBox(), new GridData());
		add(bindingLabel = new Label(), new GridData(GridData.BEGINNING,GridData.BEGINNING,true,false));
		checkBox.setBorder(new RectangularDropShadowLineBorder(1));
		minSize=new Dimension(10,10);
	}

	@Override
	public ILabel getBindingLabel() {
		return bindingLabel;
	}

}
