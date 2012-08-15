package org.opaeum.uim.figures.controls;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimDropdownFigure extends Figure implements IControlFigure{
	private Label textField = new Label();
	private JComboBox combo = new JComboBox();
	public UimDropdownFigure(){
		super();
		minSize = new Dimension(10, 10);
	}
	@Override
	public ILabel getBindingLabel(){
		return textField;
	}
	@Override
	public void paint(Graphics graphics){
		GraphicsBridge create = GraphicsBridge.buildBridge(graphics, this, combo);
		combo.setModel(new DefaultComboBoxModel(new String[]{
				textField.getText(),"sdgh"
		}));
		combo.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(155, 155, 155), new Color(200, 200, 200)));
		combo.setSelectedIndex(0);
		combo.doLayout();
		combo.paint(create);
		create.dispose();
	}
}
