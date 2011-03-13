package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UIMTabFigure extends Figure implements IContainerFigure, ILabelFigure {
	private ILabel label;
	private IFigure contentPane;


	public UIMTabFigure() {
		createContents();
	}

	protected void createContents() {
		LayoutManager layout = createLayout();
		setLayoutManager(layout);

		label = createLabel();
		label.getInsets().bottom=15;
		label.getInsets().top=15;
		label.getInsets().right=15;
		label.getInsets().left=15;
		label.setBorder(null);
		label.setOpaque(true);
		setOpaque(true);
		contentPane = createContainer();
		add(contentPane, new GridData(GridData.FILL_BOTH));
	}

	/**
	 * Creates the layout manager
	 * 
	 * @return the layout manager
	 */
	protected LayoutManager createLayout() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		return gridLayout;
	}


	protected ILabel createLabel() {
		ILabel label = new EditableLabel();
			((EditableLabel) label).setAlignment(PositionConstants.LEFT);
		return label;
	}

	protected IFigure createContainer() {
		Figure container = new Figure();
		container.setLayoutManager(new XYLayout());
		container.setOpaque(true);
		container.setBorder(new LineBorder(1));
		return container;
	}

	public ILabel getLabel() {
		return label;
	}

	public IFigure getContentPane() {
		return contentPane;
	}

	public void setLabel(ILabel h) {
		label = h;
	}

	public void setContentPane(IFigure pane) {
		contentPane = pane;
	}

}
