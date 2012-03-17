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
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.os.OSSupport;

public abstract class AbstractPanelFigure extends RoundedRectangle implements ISWTFigure{
	protected GridLayoutComposite widget;
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
		widget = new GridLayoutComposite(parent, SWT.NONE);
		widget.layout();
		parent.layout();
	}
	@Override
	public Rectangle getBounds(){
		if(getParent() instanceof HackedDefaultSizeNodeFigure){
			if(!widget.isDisposed()){
				Rectangle rec = UimFigureUtil.toDraw2DRectangle(widget);
				return rec;
			}else{
				return super.getBounds();
			}
		}else{
			return super.getBounds();
		}
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
		super.layout();
		widget.layout();
		Control[] children2 = widget.getContentPane().getChildren();
		for(Control control:children2){
			IFigure f = (IFigure) control.getData(UimFigureUtil.FIGURE);
			control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		}
		List<Figure> childFigures = getChildren();
		for(Figure figure:childFigures){
			if(figure instanceof ResizableCompartmentFigure){
				Rectangle bnds = null;
				bnds = super.getBounds().getCopy();
				bnds.y += 25;
				bnds.height -= 25;
				figure.setBounds(bnds);
			}
		}
	}
	public ResizableCompartmentFigure findCompartment(){
		List<Figure> children = getChildren();
		ResizableCompartmentFigure compartment = null;
		for(Figure figure:children){
			if(figure instanceof ResizableCompartmentFigure){
				compartment = (ResizableCompartmentFigure) figure;
				break;
			}
		}
		return compartment;
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
		fFigureGridPanelNameFigure = new WrappingLabel(){
			@Override
			public void setBounds(Rectangle rect){
				super.setBounds(rect);
			}
		};
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