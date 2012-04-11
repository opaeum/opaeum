package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class CustomBuiltInLinkFigure extends RectangleFigure implements ISWTFigure{
	private WrappingLabel fFigureBuiltInLinkNameFigure;
	private Label link;
	public CustomBuiltInLinkFigure(Composite c){
		
		this.link = new Label(c, SWT.NONE);
		this.link.setText("BuiltInLink");
		this.link.setForeground(ColorConstants.blue);
		this.link.setLayoutData(new GridData(100, 20));
		FlowLayout layoutThis = new FlowLayout();
		layoutThis.setStretchMinorAxis(false);
		layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		layoutThis.setMajorSpacing(5);
		layoutThis.setMinorSpacing(5);
		layoutThis.setHorizontal(true);
		this.setLayoutManager(layoutThis);
		createContents();
	}
	public Label getLink(){
		return link;
	}
	private void createContents(){
		fFigureBuiltInLinkNameFigure = new WrappingLabel();
		fFigureBuiltInLinkNameFigure.setText("<...>");
		this.add(fFigureBuiltInLinkNameFigure);
	}
	public WrappingLabel getFigureBuiltInLinkNameFigure(){
		return fFigureBuiltInLinkNameFigure;
	}
	@Override
	public Control getWidget(){
		return link;
	}
	@Override
	public void setLabelText(String string){
		link.setText(string);
	}
	public WrappingLabel getFigureLinkToQueryNameFigure(){
		return getFigureBuiltInLinkNameFigure();
	}
	public void paint(Graphics graphics){
		super.paint(graphics);
	}
}