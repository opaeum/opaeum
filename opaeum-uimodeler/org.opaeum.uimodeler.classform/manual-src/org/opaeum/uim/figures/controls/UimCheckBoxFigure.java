package org.opaeum.uim.figures.controls;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimCheckBoxFigure extends Figure implements IControlFigure{
	private JCheckBox checkBox = new JCheckBox();
	private Label bindingLabel;
	public UimCheckBoxFigure(){
		super();
		setLayoutManager(new GridLayout(2, false));
		minSize = new Dimension(10, 10);
	}
	@Override
	public ILabel getBindingLabel(){
		return bindingLabel;
	}
	@Override
	public void paint(Graphics graphics){
		checkBox.setBounds(new Rectangle(getBounds().x + 2, getBounds().y + 2, getBounds().height - 4, getBounds().height - 4));
		GraphicsBridge g2 = new GraphicsBridge(graphics);
		checkBox.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(155, 155, 155), new Color(55, 55, 55)));
		checkBox.doLayout();
		checkBox.paint(g2.create(getBounds().x + 2, getBounds().y + 2, getBounds().width - 4, getBounds().height - 4));
		graphics.popState();
	}
}
