package org.opaeum.uimodeler.common.figures;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.os.OSSupport;

public abstract class AbstractPanelFigure extends RoundedRectangle implements ISWTFigure{
	protected Composite widget;
	protected abstract int getColumnCount();
	long lastTimeShotsWereMade = System.currentTimeMillis();
	private WrappingLabel fFigureGridPanelNameFigure;
	public AbstractPanelFigure(){
		super();
	}
	public AbstractPanelFigure(Composite parent,Dimension cornerDimensions){
		this.setCornerDimensions(cornerDimensions);
		this.setFill(false);
		this.setLineWidth(3);
		createContents();
		widget = new Composite(parent, SWT.NONE);
		widget.setLayout(new GridLayout(getColumnCount(), false));
	}
	@Override
	public void setLayoutManager(LayoutManager manager){
		super.setLayoutManager(manager);
	}
	@Override
	public void invalidate(){
		super.invalidate();
	}
	@Override
	public void invalidateTree(){
		super.invalidateTree();
	}
	@Override
	public void validate(){
		super.validate();
	}
	@Override
	public void revalidate(){
		super.revalidate();
	}
	@Override
	protected boolean isValidationRoot(){
		return false;
	}
	@Override
	public void setBounds(Rectangle rect){
		super.setBounds(rect);
	}
	@Override
	protected void layout(){
		Rectangle bnds = getBounds();
		Rectangle nameBounds = new Rectangle();
		nameBounds.x = bnds.x;
		nameBounds.y = bnds.y;
		nameBounds.width = bnds.width;
		nameBounds.height = 20;
		fFigureGridPanelNameFigure.setBounds(nameBounds);
		List<Figure> children = getChildren();
		for(Figure figure:children){
			if(figure instanceof ResizableCompartmentFigure){
				Rectangle contentBounds = new Rectangle();
				contentBounds.x = bnds.x;
				contentBounds.y = bnds.y + 20;
				contentBounds.width = bnds.width;
				contentBounds.height = bnds.height - 20;
				figure.setBounds(contentBounds);
			}
		}
		widget.setBounds(0, 0, bnds.width - 20, getBounds().height - 20);
		widget.layout();
		for(Control control:widget.getChildren()){
			IFigure f = (IFigure) control.getData(UimFigureUtil.FIGURE);
			IFigure parent = f.getParent();
			parent.setBounds(UimFigureUtil.toDraw2DRectangle(control.getBounds()));
			f.setBounds(UimFigureUtil.toDraw2DRectangle(control.getBounds()));
			// Avoid invalidating the tree at this point VOODOO code!!!! Somehow it works
			UimFigureUtil.applyBounds(parent.getBounds(), control.getBounds());
			// UimFigureUtil.applyBounds(f.getBounds(), control.getBounds());
			control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		}
	}
	@Override
	protected void paintClientArea(Graphics graphics){
		try{
			if(Boolean.TRUE.equals(widget.getData("NEEDS_LAYOUT"))){
				layout();
				widget.setData("NEEDS_LAYOUT", null);
			}
			if(WindowBuilderUtil.needsComponentShot(widget)){
				WindowBuilderUtil.activateRootComposite(widget);
				long start = System.currentTimeMillis();
				OSSupport.get().beginShot(widget);
				OSSupport.get().makeShots(widget);
				OSSupport.get().endShot(widget);
				System.out.println("Shot took " + (System.currentTimeMillis() - start));
				WindowBuilderUtil.clearNeedsImage(widget);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		super.paintClientArea(graphics);
	}
	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
	}
	/**
	 * @generated NOT
	 */
	protected void createContents(){
		fFigureGridPanelNameFigure = new WrappingLabel();
		fFigureGridPanelNameFigure.setText("");
		this.add(fFigureGridPanelNameFigure, null);
	}
	@Override
	public void add(IFigure figure,Object constraint,int index){
		super.add(figure, constraint, index);
	}
	/**
	 * @generated NOT
	 */
	public WrappingLabel getFigureGridPanelNameFigure(){
		return fFigureGridPanelNameFigure;
	}
	@Override
	public Control getWidget(){
		return widget;
	}
	@Override
	public void setLabelText(String string){
		fFigureGridPanelNameFigure.setText(string);
	}
}