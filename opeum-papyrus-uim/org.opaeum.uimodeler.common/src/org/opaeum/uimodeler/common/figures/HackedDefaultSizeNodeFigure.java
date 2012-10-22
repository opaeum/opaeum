package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uimodeler.common.UimFigureUtil;

public class HackedDefaultSizeNodeFigure extends DefaultSizeNodeFigure{
	private ISWTFigure primaryChild;
	public HackedDefaultSizeNodeFigure(ISWTFigure primaryChild){
		super(primaryChild instanceof CustomGridPanelFigure ? new Dimension(100, 100) : primaryChild.getSize());
		this.primaryChild = primaryChild;
	}
	public Rectangle getOriginalBounds(){
		return super.getBounds();
	}
	@Override
	public Rectangle getBounds(){
		Control widget = (Control) primaryChild.getWidget();
		Rectangle r = UimFigureUtil.toDraw2DRectangle(widget).getCopy();
		return r;
	}
	@Override
	public void setBounds(Rectangle rect){
		super.setBounds(rect);
	}
	@Override
	protected boolean useLocalCoordinates(){
		return false;
	}
	@Override
	public void paint(Graphics graphics){
		if(primaryChild instanceof AbstractPanelFigure || primaryChild.getClass().getSimpleName().equals("CustomUimDataTableFigure")){
			super.paint(graphics);
		}else{
			Control widget = (Control) primaryChild.getWidget();
			Image img = (Image) widget.getData(UimFigureUtil.OPAEUM_IMAGE);
			if(widget.isDisposed()){
				try{
					graphics.drawImage(img, getBounds().x, getBounds().y);
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				org.eclipse.swt.graphics.Rectangle bounds2 = widget.getBounds();
				if(bounds2.width > 0 && bounds2.height > 0){
					try{
//						 Shell shell = new Shell(widget.getDisplay());
//						 shell.setBackgroundImage(img);
//						 shell.open();
						if(img!=null){
						graphics.drawImage(img, getBounds().x, getBounds().y);
						}else{
							System.out.println("NULL IMAGE");
						}
					}catch(Exception e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
