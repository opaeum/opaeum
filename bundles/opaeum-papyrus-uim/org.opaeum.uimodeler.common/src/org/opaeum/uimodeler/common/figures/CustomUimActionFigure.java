package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uim.swt.UimRuntimeActivator;

public abstract class CustomUimActionFigure extends RectangleFigure implements ISWTFigure{
	protected ButtonComposite button;
	protected abstract void createContents();
	public CustomUimActionFigure(Composite parent){
		createContents();
		button = new ButtonComposite(parent, SWT.NONE);
		button.button.setImage(UimRuntimeActivator.getDefault().getImage("/icons/operation.ico"));
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