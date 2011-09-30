package org.opeum.topcased.bpmn2;

import java.net.URL;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.opeum.topcased.classdiagram.figure.Gradient;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.layout.BorderAttachedLayout;

public class Bpmn2IntermediateEventFigure extends Figure implements IContainerFigure,ILabelFigure,HandleBounds{
	private ILabel label;
	private IFigure contentPane;
	private IFigure innerCircle;
	private ImageFigure imageFigure;
	public Bpmn2IntermediateEventFigure(){
		LayoutManager layout = new StackLayout();
		setLayoutManager(layout);
		final IFigure outerRectangle = new Figure();
		outerRectangle.setOpaque(false);
		outerRectangle.setLayoutManager(new BorderLayout());
		IFigure outerCircle = new Ellipse(){
		};
		outerCircle.setLayoutManager(new StackLayout());
		outerCircle.setBorder(new MarginBorder(10));
		outerCircle.setOpaque(false);
		outerRectangle.add(outerCircle, new Integer(PositionConstants.CENTER));
		innerCircle = createBackgroundFigure();
		innerCircle.setLayoutManager(new StackLayout());
		innerCircle.setOpaque(true);
		SVGFigure figure = new SVGFigure();
		figure.setSpecifyCanvasWidth(true);
		URL entry = Bpmn2Plugin.getDefault().getBundle().getEntry("icons/full/objsvg/ManualTask.svg");
		figure.setURI(entry.toString());
		innerCircle.add(figure);
		outerCircle.add(innerCircle);
		EditableLabel nameLbl = new EditableLabel(true);
		nameLbl.setTextJustification(PositionConstants.CENTER);
		label = new ComposedLabel(new Label(), nameLbl, null, false);
		label.setOpaque(false);
		outerRectangle.add(label, new Integer(PositionConstants.BOTTOM));
		add(outerRectangle);
		contentPane = new Figure();
		contentPane.setOpaque(false);
		contentPane.setLayoutManager(new BorderAttachedLayout());
		add(contentPane);
		label.addLayoutListener(new LayoutListener(){
			long lastRefresh;
			@Override
			public void setConstraint(IFigure child,Object constraint){
			}
			@Override
			public void remove(IFigure child){
			}
			@Override
			public void postLayout(IFigure container){
				if(System.currentTimeMillis()-lastRefresh>1000){
					Rectangle rect = Bpmn2IntermediateEventFigure.this.getBounds().getCopy();
					rect.width= rect.width + label.getSize().height;
					lastRefresh=System.currentTimeMillis();
					Bpmn2IntermediateEventFigure.this.setBounds(rect);
				}
			}
			@Override
			public boolean layout(IFigure container){
				return false;
			}
			@Override
			public void invalidate(IFigure container){
				// TODO Auto-generated method stub
			}
		});
	}
	@Override
	public void setBounds(Rectangle rect2){
		Rectangle rect = rect2.getCopy();
		Rectangle bounds = getBounds();
		if(rect.width == bounds.width && rect.height != bounds.height){
			int estimatedWidth = rect.height - label.getSize().height;
			label.setSize(estimatedWidth, label.getSize().height);
			Dimension labelSize = label.getPreferredSize(estimatedWidth, rect.height);
			// first estimate width
			rect.width = Math.abs(rect.height - labelSize.height);
		}else if(rect.height == bounds.height && rect.width != bounds.width){
			label.setSize(rect.width, label.getSize().height);
			Dimension labelSize = label.getPreferredSize(rect.width, rect.height);
			rect.height = Math.abs(rect.width + labelSize.height);
		}else if(rect.height != bounds.height && rect.width != bounds.width){
			label.setSize(rect.width, label.getSize().height);
			Dimension labelSize = label.getPreferredSize(rect.width, rect.height);
			rect.height = Math.abs(rect.width + labelSize.height);
		}
		super.setBounds(rect);
	}
	@Override
	public void setSize(int w,int h){
		super.setSize(w, h);
	}
	@Override
	public Dimension getPreferredSize(int wHint,int hHint){
		return new Dimension(80, 80);
	}
	protected IFigure createBackgroundFigure(){
		return new Ellipse(){
			@Override
			public void paint(Graphics graphics){
				Rectangle originalBounds = this.getBounds();
				Rectangle r = originalBounds.getCopy();
				r.x=(int) Math.round(r.x*graphics.getAbsoluteScale());
				r.y=(int) Math.round(r.y*graphics.getAbsoluteScale());
				r.height=(int) Math.round(r.height*graphics.getAbsoluteScale());
				r.width=(int) Math.round(r.width*graphics.getAbsoluteScale());
//				graphics.setBackgroundColor(ColorConstants.black);
				Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y+r.height, ColorConstants.black, ColorConstants.white);
				graphics.setBackgroundPattern(bcPattern);
				graphics.setFillRule(SWT.FILL_EVEN_ODD);
				graphics.fillOval(originalBounds.getCopy());
				graphics.setBackgroundPattern(null);
				bcPattern.dispose();
				super.paintChildren(graphics);
			}

		};
	}
	public IFigure getBackgroundFigure(){
		return innerCircle;
	}
	public ILabel getLabel(){
		return label;
	}
	public IFigure getContentPane(){
		return contentPane;
	}
	public Rectangle getHandleBounds(){
		return getBounds();
	}
	public IFigure getToolTip(){
		return getLabel().getToolTip();
	}
	public ImageFigure getImageFigure(){
		return imageFigure;
	}
}
