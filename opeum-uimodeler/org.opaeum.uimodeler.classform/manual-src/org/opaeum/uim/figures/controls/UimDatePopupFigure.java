package org.opeum.uim.figures.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Date;

import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

import com.toedter.calendar.JCalendar;

public class UimDatePopupFigure extends Figure implements IControlFigure{
	Label text = new Label();
	JCalendar calendar = new JCalendar(true);
	@Override
	public ILabel getBindingLabel(){
		return text;
	}
	public void paint(Graphics graphics){
		GraphicsBridge create = GraphicsBridge.buildBridge(graphics, this, calendar);
		calendar.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(155, 155, 155), new Color(200, 200, 200)));
		calendar.setDate(new Date());
		calendar.doLayout();
		Component[] components = calendar.getComponents();
		for(Component component:components){
			component.doLayout();
			if(component instanceof Container){
				Component[] components2 = ((Container) component).getComponents();
				for(Component component2:components2){
					component2.doLayout();
					if(component2 instanceof Container){
						Component[] components3 = ((Container) component2).getComponents();
						for(Component component3:components3){
							component3.doLayout();
						}
					}
				}
			}
		}
		calendar.paint(create);
		create.dispose();
	}
	public static void main(String[] args){
		JCalendar.main(args);
	}
}
