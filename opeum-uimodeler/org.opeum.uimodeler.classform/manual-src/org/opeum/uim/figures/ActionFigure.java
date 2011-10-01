package org.opeum.uim.figures;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.opeum.uim.figures.controls.GraphicsBridge;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;

/**
 * The figure to display a User Interaction Diagram.
 * 
 * @generated NOT
 */
public class ActionFigure extends Figure implements ILabelFigure{
	JButton button = new JButton();
	private EditableLabel label = new EditableLabel(){
		public void setText(String text){
			button.setText(text);
		}
	};
	public ActionFigure(){
		super();
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 1;
		gridLayout.marginHeight = 1;
		GridLayout layout = gridLayout;
		setLayoutManager(layout);
		label = new EditableLabel();
		label.setTextAlignment(Label.RIGHT);
		add(label,new GridData(GridData.CENTER,GridData.CENTER,true,true));
		super.setOpaque(true);
	}
	public Label getLabel(){
		return label;
	}
	@Override
	public void paint(Graphics graphics){
		label.setBounds(getBounds());
		GraphicsBridge g2 = GraphicsBridge.buildBridge(graphics, this, button);
		button.setText(label.getText());
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		button.doLayout();
		button.paint(g2);
		graphics.popState();
	}
}
