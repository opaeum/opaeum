package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;

public class HackedDefaultSizeNodeFigure extends DefaultSizeNodeFigure{
	private ISWTFigure primaryChild;
	public HackedDefaultSizeNodeFigure(ISWTFigure primaryChild){
		super(primaryChild instanceof CustomGridPanelFigure ? new Dimension(100, 100) : primaryChild.getSize());
		this.primaryChild = primaryChild;
	}
	@Override
	public Rectangle getBounds(){
		Control widget = (Control) primaryChild.getWidget();
		if(!widget.isDisposed()){
			Rectangle r = UimFigureUtil.toDraw2DRectangle(widget).getCopy();
			if(widget instanceof UimDataTableComposite){
				// TODO Find a better place for this
//				r.height -= ((UimDataTableComposite) widget).getFirstRow().getSize().x;
			}
			return r;
		}else{
			return super.getBounds();
		}
	}
	@Override
	protected boolean useLocalCoordinates(){
		return false;
	}
	@Override
	public void paint(Graphics graphics){
		if(primaryChild instanceof AbstractPanelFigure || primaryChild.getClass().getSimpleName().equals("CustomUimDataTableFigure")){
			// setBackgroundColor(ColorConstants.black);
			super.paint(graphics);
		}else{
			org.eclipse.swt.graphics.Rectangle bounds2 = primaryChild.getWidget().getBounds();
			if(bounds2.width > 0 && bounds2.height > 0){
				final Image image = new Image(null, bounds2);
				// GC gc = new GC(image);
				// getComposite().print(gc)
				;
				Point copy = getLocation().getCopy();
				try{
					graphics.drawImage((Image) primaryChild.getWidget().getData("OPAEUM_IMAGE"), getBounds().x, getBounds().y);
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
