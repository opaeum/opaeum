package org.opaeum.uimodeler.common.figures;

import java.io.IOException;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uim.swt.UimActivator;
import org.opaeum.uimodeler.common.UimFigureUtil;

public abstract class CustomUimActionFigure extends RectangleFigure implements ISWTFigure{
	protected ButtonComposite button;
	protected abstract void createContents();
	public CustomUimActionFigure(Composite parent){
		createContents();
		button = new ButtonComposite(parent, SWT.NONE); 

		try{
			button.button.setImage(new Image(Display.getCurrent(), UimActivator.INSTANCE.getBundle().getResource("/icons/operation.ico").openStream()));
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.markForShot();
	}
	protected void paintClientArea(Graphics graphics){
		super.paintClientArea(graphics);
	}
	public Button getButton(){
		return button.button;
	}
	@Override
	public IUimWidget getWidget(){
		return button;
	}
	public void setLabelText(String string){
		button.button.setText(string);
	}
}