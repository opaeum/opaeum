package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;

/**
 * The figure to display a User Interaction Diagram.
 * 
 * @generated NOT
 */
public class ActionFigure extends Figure implements ILabelFigure {
	Label label;
	Label arrow;
	Label action;
	Label end;

	public ActionFigure() {
		super();
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 1;
		gridLayout.marginHeight = 1;
		GridLayout layout = gridLayout;
		setLayoutManager(layout);
		label = new EditableLabel();
		SimpleRaisedBorder srb = new SimpleRaisedBorder(1);
		add(label, new GridData());
		add(arrow=new Label("["), new GridData());
		add(action=new Label(), new GridData());
		add(end=new Label("]"), new GridData());
		setBorder(srb);
		super.setOpaque(true);
	}

	public Label getActionFigure() {
		return action;
	}

	public Label getLabel() {
		return label;
	}


}
