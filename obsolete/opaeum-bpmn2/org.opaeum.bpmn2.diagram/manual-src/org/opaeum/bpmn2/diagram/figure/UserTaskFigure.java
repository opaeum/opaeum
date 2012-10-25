package org.opaeum.bpmn2.diagram.figure;

import java.io.InputStream;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.SVGImageConverter;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.factory.RenderedImageKey;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.opaeum.bpmn2.modeleditor.Bpmn2Plugin;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UserTaskFigure extends RoundedRectangle implements ILabelFigure{
	@Override
	protected void fillShape(Graphics graphics){
		Rectangle originalBounds = this.getBounds();
		Rectangle r = originalBounds.getCopy();
		r.x = (int) Math.round(r.x * graphics.getAbsoluteScale());
		r.y = (int) Math.round(r.y * graphics.getAbsoluteScale());
		r.height = (int) Math.round(r.height * graphics.getAbsoluteScale());
		r.width = (int) Math.round(r.width * graphics.getAbsoluteScale());
		// graphics.setBackgroundColor(ColorConstants.black);
		Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y + r.height, getBackgroundColor(), ColorConstants.white);
		graphics.setBackgroundPattern(bcPattern);
		graphics.setFillRule(SWT.FILL_EVEN_ODD);
		graphics.fillRoundRectangle(originalBounds.getCopy(),getCornerDimensions().width,getCornerDimensions().height);
		graphics.setBackgroundPattern(null);
		bcPattern.dispose();
	}
	@Override
	protected void outlineShape(Graphics graphics){
		// TODO Auto-generated method stub
		super.outlineShape(graphics);
	}
	private ILabel label;
	private String userTaskId;
	public UserTaskFigure(){
		LayoutManager layout = new BorderLayout();
		setLayoutManager(layout);
		setOpaque(true);
		add(createImageFigure(),BorderLayout.CENTER);
		add(label=new EditableLabel(), BorderLayout.BOTTOM);

	}
	private IFigure createImageFigure(){
		// TODO Auto-generated method stub
		return new Figure(){

			@Override
			public void paint(Graphics graphics){
				SVGImageConverter ic = new SVGImageConverter();
				RenderedImageKey inf = new RenderedImageKey();
				Image im = new Image(Display.getDefault(), 20, 20);
				inf.setValues(getBounds().width, getBounds().height, true, true, getBackgroundColor().getRGB(), null);
				InputStream str;
				try{
					str = Bpmn2Plugin.getDefault().getBundle().getEntry("icons/full/objsvg/User.svg").openStream();
					// str = BPMN2Plugin.getDefault().getBundle().getEntry("images/test.svg").openStream();
					Image img = ic.renderSVGtoSWTImage(str, inf);
					int transparencyType = img.getImageData().getTransparencyType() - SWT.TRANSPARENCY_PIXEL;
					PaletteData palette = new PaletteData(new RGB[]{
							getBackgroundColor().getRGB(),ColorConstants.black.getRGB()
					});
					img.getImageData().palette = palette;
					img.getImageData().transparentPixel = 0;
					graphics.drawImage(img, getBounds().x, getBounds().y);
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
	}
	protected IFigure createBackgroundFigure(){
		return new RoundedRectangle();
	}
	public ILabel getLabel(){
		return label;
	}
	public Rectangle getHandleBounds(){
		return getBounds();
	}
	public IFigure getToolTip(){
		return getLabel().getToolTip();
	}
	public String getUserTaskId(){
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId){
		this.userTaskId = userTaskId;
	}
	public void paintChildren(Graphics graphics){
		super.paintChildren(graphics);
	}
	
}
