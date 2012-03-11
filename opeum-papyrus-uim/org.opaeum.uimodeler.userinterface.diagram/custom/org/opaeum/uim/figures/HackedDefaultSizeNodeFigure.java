package org.opaeum.uim.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uimodeler.util.UimFigureUtil;

public class HackedDefaultSizeNodeFigure extends DefaultSizeNodeFigure{
	private ISWTFigure primaryChild;
	public HackedDefaultSizeNodeFigure(ISWTFigure primaryChild){
		super(new Dimension(40, 40));
		this.primaryChild = primaryChild;
	}
	@Override
	public Rectangle getBounds(){
		Control widget = (Control) primaryChild.getWidget();
		if(!widget.isDisposed()){
			return UimFigureUtil.toDraw2DRectangle(widget.getBounds());
		}else{
			return super.getBounds();
		}
	}
	@Override
	public void paint(Graphics graphics){
		primaryChild.paint(graphics);
	}
}
