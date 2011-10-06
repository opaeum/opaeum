package org.opaeum.bpmn2.diagram.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.layout.CenterLayout;

public class AbstractEventFigure extends Ellipse implements IContainerFigure {
	protected Figure contentPane;
	private String userTaskId;
	public AbstractEventFigure(){
		super();
		LayoutManager layout = new CenterLayout(){

			@Override
			public void layout(IFigure container){
				float outlineWidth = (getBounds().height + getBounds().width)/2f*0.07f;
				Rectangle cp = getBounds().getCopy();
				cp.height=cp.height-Math.round(outlineWidth*5.6f);
				cp.width=cp.width-Math.round(outlineWidth*5.6f);
				cp.x=getBounds().x+Math.round(outlineWidth*2.8f);
				cp.y=getBounds().y+Math.round(outlineWidth*2.8f);
				contentPane.setBounds(cp);
			}
			
		};
		setLayoutManager(layout);
//		final IFigure outerRectangle = new Figure();
//		outerRectangle.setOpaque(false);
//		outerRectangle.setLayoutManager(new BorderLayout());
//		IFigure outerCircle = new Ellipse();
//		outerCircle.setLayoutManager(new StackLayout());
//		outerCircle.setBorder(new MarginBorder(2));
//		outerCircle.setOpaque(false);
//		outerRectangle.add(outerCircle, new Integer(PositionConstants.CENTER));
//		innerCircle = createBackgroundFigure();
//		innerCircle.setLayoutManager(new StackLayout());
//		innerCircle.setOpaque(true);
//		SVGFigure figure = new SVGFigure();
//		figure.setSpecifyCanvasWidth(true);
//		URL entry = Bpmn2EditPlugin.getPlugin().getBundle().getEntry(calcImage());
//		figure.setURI(entry.toString());
//		innerCircle.add(figure);
//		outerCircle.add(innerCircle);
//		EditableLabel nameLbl = new EditableLabel(true);
//		nameLbl.setTextJustification(PositionConstants.CENTER);
//		add(outerRectangle);
		contentPane = createBackgroundFigure();
		contentPane.setOpaque(true);
		
		contentPane.setLayoutManager(new FullLayoutManager());
//		add(new Label(),BorderLayout.TOP);
//		add(new Label(),BorderLayout.BOTTOM);
//		add(new Label(),BorderLayout.RIGHT);
//		add(new Label(),BorderLayout.LEFT);
		add(contentPane);
	}
	public void paintChildren(Graphics graphics){
		super.paintChildren(graphics);
	}
	protected int getOutlineWidth(){
		return Math.round(((getBounds().height + getBounds().width)/2f*0.07f)) ;
	}
	@Override
	public void paintFigure(Graphics graphics){
		super.paintFigure(graphics);
	}
	@Override
	protected void fillShape(Graphics graphics){
		Rectangle originalBounds = this.getBounds();
		Rectangle r = originalBounds.getCopy();
		r.x = (int) Math.round(r.x * graphics.getAbsoluteScale());
		r.y = (int) Math.round(r.y * graphics.getAbsoluteScale());
		r.height = (int) Math.round(r.height * graphics.getAbsoluteScale());
		r.width = (int) Math.round(r.width * graphics.getAbsoluteScale());
		// graphics.setBackgroundColor(ColorConstants.black);
		Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y + r.height, AbstractEventFigure.this.getBackgroundColor(), ColorConstants.white);
		graphics.setBackgroundPattern(bcPattern);
		graphics.setFillRule(SWT.FILL_EVEN_ODD);
		Rectangle bounds2 = getBounds().getCopy();
		bounds2.height=bounds2.height-getOutlineWidth()*2;
		bounds2.width=bounds2.width-getOutlineWidth()*2;
		bounds2.y=bounds2.y+getOutlineWidth();
		bounds2.x=bounds2.x+getOutlineWidth();
		graphics.fillOval(bounds2);
		graphics.setBackgroundPattern(null);
		bcPattern.dispose();
	}
	public String getUserTaskId(){
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId){
		this.userTaskId = userTaskId;
	}
	protected Figure createBackgroundFigure(){
		return new Ellipse(){
			@Override
			public void paint(Graphics graphics){
				super.paintChildren(graphics);
			}
		};
	}
	@Override
	public IFigure getContentPane(){
		return contentPane;
	}
}