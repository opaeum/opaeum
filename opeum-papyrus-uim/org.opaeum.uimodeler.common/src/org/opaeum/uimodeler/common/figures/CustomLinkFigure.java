package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Widget;

public class CustomLinkFigure extends RectangleFigure implements ISWTFigure{
	private WrappingLabel fFigureBuiltInLinkNameFigure;
	private Link link;
	public CustomLinkFigure(Composite c){
		this.link = new Link(c, SWT.NONE);
		this.link.setText("BuiltInLink");
		this.link.setForeground(ColorConstants.blue);
		this.link.setLayoutData(new GridData(100,20));
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
	private void createContents(){
		fFigureBuiltInLinkNameFigure = new WrappingLabel();
		fFigureBuiltInLinkNameFigure.setText("<...>");
		this.add(fFigureBuiltInLinkNameFigure);
	}
	public WrappingLabel getFigureBuiltInLinkNameFigure(){
		return fFigureBuiltInLinkNameFigure;
	}
	@Override
	public Widget getWidget(){
		return link;
	}
	@Override
	public void setLabelText(String string){
		link.setText(string);
	}
	@Override
	public void markForRepaint(){
	}
	public WrappingLabel getFigureLinkToQueryNameFigure(){
		return getFigureBuiltInLinkNameFigure();
	}
	public void paint(Graphics graphics){
		org.eclipse.swt.graphics.Rectangle bounds2 = ((Control) getWidget()).getBounds();
		if(bounds2.width > 0 && bounds2.height > 0){
			final Image image = new Image(null, bounds2);
			// GC gc = new GC(image);
			// getComposite().print(gc)
			;
			Point copy = getLocation().getCopy();
			try{
				graphics.drawImage((Image) getWidget().getData("OPAEUM_IMAGE"), copy.x, copy.y);
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}