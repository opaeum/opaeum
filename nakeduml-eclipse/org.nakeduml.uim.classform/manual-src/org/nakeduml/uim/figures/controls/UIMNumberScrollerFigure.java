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

public class UIMNumberScrollerFigure extends Figure implements IControlFigure {
	private Label textField;
	private ArrowButton upAarrowButton;
	private ArrowButton downAarrowButton;

	public UIMNumberScrollerFigure() {
		super();
		minSize=new Dimension(10,10);
		final GridLayout gl = new GridLayout(2, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.horizontalSpacing = 0;
		gl.verticalSpacing = 0;
		setLayoutManager(gl);
		textField = new Label();
		add(textField, new GridData(GridData.CENTER, GridData.CENTER, true,
				false));

		Figure scroller = new Figure();
		GridLayout scrollerLayout = new GridLayout(1, false);
		scrollerLayout.marginHeight = 0;
		scrollerLayout.marginWidth = 0;
		scrollerLayout.verticalSpacing = 0;
		scrollerLayout.horizontalSpacing = 0;
		scroller.setLayoutManager(scrollerLayout);
		scroller.setPreferredSize(new Dimension(20, 20));
		upAarrowButton = new ArrowButton(ArrowButton.NORTH);
		upAarrowButton.setPreferredSize(new Dimension(20, 10));
		scroller.add(upAarrowButton);
		downAarrowButton = new ArrowButton(ArrowButton.SOUTH);
		downAarrowButton.setPreferredSize(new Dimension(20, 10));
		scroller.add(downAarrowButton);
		add(scroller, new GridData(GridData.END, GridData.END, true, true));
		setBorder(new RectangularDropShadowLineBorder(1));
	}

	@Override
	public ILabel getBindingLabel() {
		return textField;
	}

}
