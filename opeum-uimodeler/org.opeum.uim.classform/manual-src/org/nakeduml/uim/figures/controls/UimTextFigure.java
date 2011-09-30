package org.opeum.uim.figures.controls;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimTextFigure extends Label implements IControlFigure{
	private Label textField = new Label();
	private JTextField text = new JTextField();
	public UimTextFigure(){
		super();
		minSize = new Dimension(10, 10);
	}
	public UimTextFigure(String string){
		text.setText(string);
	}
	@Override
	public ILabel getBindingLabel(){
		return textField;
	}
	@Override
	public void paint(Graphics graphics){
		
		GraphicsBridge create = GraphicsBridge.buildBridge(graphics, this, text);
		text.setText(textField.getText());
		text.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(155, 155, 155), new Color(200, 200, 200)));
		text.doLayout();
		text.paint(create);
		create.dispose();
	}
}
