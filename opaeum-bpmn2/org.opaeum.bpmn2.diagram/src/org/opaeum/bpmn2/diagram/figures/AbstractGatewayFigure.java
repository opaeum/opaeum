package org.opaeum.bpmn2.diagram.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.topcased.draw2d.layout.CenterLayout;

public class AbstractGatewayFigure extends Shape{
	public AbstractGatewayFigure(){
		super();
		final SVGFigure imageFigure = new SVGFigure(getImagePath());
		setLayoutManager(new CenterLayout(){
			@Override
			public void layout(IFigure figure){
				Rectangle bounds2 = getBounds();
				Rectangle imageBounds = new Rectangle();
				float f = 0.65f;
				imageBounds.x = Math.round(bounds2.x * f);
				imageBounds.y = Math.round(bounds2.y * f);
				imageBounds.width = Math.round(bounds2.width * f);
				imageBounds.height = Math.round(bounds2.height * f);
				imageFigure.setBounds(imageBounds);
				super.layout(figure);
			}
		});
		add(imageFigure);
	}
	protected String getImagePath(){
		return "icons/full/objsvg/Parallel.svg";
	}
	protected void fillShape(Graphics graphics){
		Rectangle f = Rectangle.SINGLETON;
		f.x = getBounds().x + getOutlineWidth(getBounds()) * 1;
		f.y = getBounds().y + getOutlineWidth(getBounds()) * 1;
		f.width = getBounds().width - getOutlineWidth(getBounds()) * 2;
		f.height = getBounds().height - getOutlineWidth(getBounds()) * 2;
		Rectangle r = f.getCopy();
		r.x = (int) Math.round(r.x * graphics.getAbsoluteScale());
		r.y = (int) Math.round(r.y * graphics.getAbsoluteScale());
		r.height = (int) Math.round(r.height * graphics.getAbsoluteScale());
		r.width = (int) Math.round(r.width * graphics.getAbsoluteScale());
		PointList pl = new PointList();
		pl.addPoint(f.getTop());
		pl.addPoint(f.getRight());
		pl.addPoint(f.getBottom());
		pl.addPoint(f.getLeft());
		Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y + r.height, getBackgroundColor(), ColorConstants.white);
		graphics.setBackgroundPattern(bcPattern);
		graphics.setFillRule(SWT.FILL_EVEN_ODD);
		graphics.fillPolygon(pl);
		bcPattern.dispose();
	}
	protected void outlineShape(Graphics graphics){
		Rectangle f = Rectangle.SINGLETON;
		Rectangle r = getBounds();
		f.x = r.x + getOutlineWidth(getBounds()) * 1;
		f.y = r.y + getOutlineWidth(getBounds()) * 1;
		f.width = r.width - getOutlineWidth(getBounds()) * 2;
		f.height = r.height - getOutlineWidth(getBounds()) * 2;
		PointList pl = new PointList();
		pl.addPoint(f.getTop());
		pl.addPoint(f.getRight());
		pl.addPoint(f.getBottom());
		pl.addPoint(f.getLeft());
		Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y + r.height, getBackgroundColor(), ColorConstants.white);
		graphics.setBackgroundPattern(bcPattern);
		graphics.setFillRule(SWT.FILL_EVEN_ODD);
		Rectangle bounds2 = getBounds().getCopy();
		bounds2.height = bounds2.height - getOutlineWidth(r) * 2;
		bounds2.width = bounds2.width - getOutlineWidth(r) * 2;
		bounds2.y = bounds2.y + getOutlineWidth(r);
		bounds2.x = bounds2.x + getOutlineWidth(r);
		graphics.setLineWidth(getOutlineWidth(r));
		graphics.drawPolygon(pl);
	}
	private int getOutlineWidth(Rectangle r){
		return Math.round(0.02f * (r.width + r.height / 2f));
	}
}
