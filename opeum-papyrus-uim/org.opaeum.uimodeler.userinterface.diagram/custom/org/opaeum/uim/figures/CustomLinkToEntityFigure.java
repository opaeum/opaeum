package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.util.UmlUimLinks;

public class CustomLinkToEntityFigure extends CustomUimFieldFigure implements ISWTFigure{
	private Link link;
	public CustomLinkToEntityFigure(Composite parent){
		super(parent);
	}
	@Override
	protected Control createDefaultControl(){
		link = new Link(getComposite(), SWT.BORDER);
		link.setForeground(ColorConstants.blue);
		link.setText("<Object Name>");
		return link;
	}
	public WrappingLabel getFigureLinkToEntityUmlElementUidFigure(){
		return super.getFigureUimFieldNameFigure();
	}
	@Override
	public Control getWidget(){
		return super.getWidget();
	}
	@Override
	public void setLabelText(String string){
		super.setLabelText(string);
	}
	protected void paintClientArea(Graphics graphics){
		Point copy = getLocation().getCopy();
		try{
			graphics.drawImage((Image) link.getData(OSSupport.WBP_IMAGE), copy.x, copy.y);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void markForRepaint(){
		super.markForRepaint();
	}
}