package org.nakeduml.uim.figures.controls;


import org.eclipse.draw2d.ArrowButton;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.nakeduml.uim.figures.IControlFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UIMDropdownFigure extends Figure implements IControlFigure {
	private Label textField;
	private ArrowButton arrowButton;

	public UIMDropdownFigure() {
		super();
		final GridLayout gl = new GridLayout(2, false);
		gl.marginHeight=0;
		gl.marginWidth=0;
		gl.horizontalSpacing=0;
		gl.verticalSpacing=0;
		setLayoutManager(gl);
		textField = new Label();
		add(textField, new GridData(GridData.CENTER, GridData.CENTER,
				true, false));
		
		arrowButton = new ArrowButton(ArrowButton.SOUTH);
		arrowButton.setPreferredSize(new Dimension(20,20));
		add(arrowButton, new GridData(GridData.END, GridData.END, true,true));
		setBorder(new RectangularDropShadowLineBorder(1));
		minSize=new Dimension(10,10);
	}

	@Override
	public ILabel getBindingLabel() {
		return textField;
	}



}
