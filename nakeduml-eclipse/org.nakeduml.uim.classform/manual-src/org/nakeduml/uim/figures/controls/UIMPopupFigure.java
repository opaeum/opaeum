package org.nakeduml.uim.figures.controls;


import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.nakeduml.uim.figures.IControlFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UIMPopupFigure extends Figure implements IControlFigure {
	private Label textField;
	private Button button;

	public UIMPopupFigure() {
		super();
		minSize=new Dimension(10,10);
		final GridLayout gl = new GridLayout(2, false);
		gl.marginHeight=1;
		gl.marginWidth=0;
		gl.horizontalSpacing=0;
		gl.verticalSpacing=0;


		setLayoutManager(gl);
		textField = new Label();
		textField.setAlignment(PositionConstants.CENTER);
		add(textField, new GridData(GridData.CENTER, GridData.CENTER,
				true, false));
		button = new Button("...");
		button.setPreferredSize(new Dimension(20,20));
		add(button, new GridData());
		setBorder(new RectangularDropShadowLineBorder(1));
	}

	@Override
	public ILabel getBindingLabel() {
		return textField;
	}
}
