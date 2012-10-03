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
import org.opaeum.uim.swt.UimActivator;

public abstract class CustomUimActionFigure extends RectangleFigure implements ISWTFigure{
	protected Button button;
	protected abstract void createContents();
	public CustomUimActionFigure(Composite parent){
//		FlowLayout layoutThis = new FlowLayout();
//		layoutThis.setStretchMinorAxis(false);
//		layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);
//		layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
//		layoutThis.setMajorSpacing(5);
//		layoutThis.setMinorSpacing(5);
//		layoutThis.setHorizontal(true);
//		this.setLayoutManager(layoutThis);
		createContents();
		button = new Button(parent, SWT.PUSH);
		try{
			button.setImage(new Image(Display.getCurrent(), UimActivator.INSTANCE.getBundle().getResource("/icons/operation.ico").openStream()));
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void paintClientArea(Graphics graphics){
		super.paintClientArea(graphics);
	}
	public Button getButton(){
		return button;
	}
	@Override
	public Control getWidget(){
		return button;
	}
	public void setLabelText(String string){
		button.setText(string);
	}
}