package org.opaeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.opaeum.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;

/**
 * The figure to display a User Interaction Diagram.
 * 
 * @generated NOT
 */
public class UimDataTableFigure extends Figure implements IContainerFigure,ILabelFigure,IBindingFigure{
	private EditableLabel label;
	private IFigure contentPane;
	private IFigure header;
	private Label bindingFigure;
	/**
	 * Constructor
	 */
	public UimDataTableFigure(){
		createContents();
	}
	/**
	 * Creates the contents of the figure : by defauft, it creates a layout manager, a header and a container
	 */
	protected void createContents(){
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayoutManager(gridLayout);
		label = new EditableLabel();
		label.setAlignment(PositionConstants.LEFT);
		bindingFigure = new Label();
		header = new Figure();
		add(header, new GridData(GridData.FILL, GridData.BEGINNING, false, false));
		GridLayout headerLayout = new GridLayout(2, false);
		headerLayout.verticalSpacing = 0;
		headerLayout.horizontalSpacing = 10;
		headerLayout.marginWidth = 0;
		headerLayout.marginHeight = 0;
		header.setLayoutManager(headerLayout);
		header.add(label, new GridData());
		header.add(bindingFigure, new GridData(GridData.END, GridData.END, true, true));
		contentPane = createContainer();
		add(contentPane, new GridData(GridData.FILL_BOTH));
	}
	protected IFigure createContainer(){
		Figure container = new Figure();
		container.setLayoutManager(new StackLayout());
		container.setOpaque(true);
		return container;
	}
	public IFigure getHeader(){
		return header;
	}
	public ILabel getLabel(){
		return label;
	}
	public IFigure getContentPane(){
		return contentPane;
	}
	@Override
	public Label getBindingLabel(){
		return bindingFigure;
	}
}
