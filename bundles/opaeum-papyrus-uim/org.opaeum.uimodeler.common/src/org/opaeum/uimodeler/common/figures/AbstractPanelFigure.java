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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uimodeler.common.UimFigureUtil;

public abstract class AbstractPanelFigure extends RoundedRectangle implements ISWTFigure{
	protected GridPanelComposite widget;
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
		widget = new GridPanelComposite(parent, SWT.NONE);
		widget.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		parent.layout();
	}
	@Override
	public Rectangle getBounds(){
		if(getParent() instanceof HackedDefaultSizeNodeFigure){
			Rectangle rec = UimFigureUtil.toDraw2DRectangle(widget);
			return rec;
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
		if(!widget.isDisposed()){
			GridData layoutData = (GridData) widget.getLayoutData();
			Dimension size = getSize();
			if(layoutData.grabExcessHorizontalSpace == false && layoutData.grabExcessVerticalSpace == false && layoutData.widthHint == -1
					&& layoutData.heightHint == -1 && (size.height < 100 || size.width < 300)){
				widget.pack();
				widget.getParent().pack();
			}
		}
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
			// Layout first to see if anything changes
			WindowBuilderUtil.printNeedsComponentShot(widget);
			WindowBuilderUtil.hideSecondLevel(widget);

//			WindowBuilderUtil.layoutRecursively(widget);
			if(WindowBuilderUtil.needsComponentShot(widget)){
				WindowBuilderUtil.activateRootComposite(widget);
				long start = System.currentTimeMillis();
				OSSupport.get().beginShot(widget);
				OSSupport.get().makeShots(widget);
				OSSupport.get().endShot(widget);
				System.out.println("Shot took " + (System.currentTimeMillis() - start));
				WindowBuilderUtil.clearNeedsImage(widget);
//				 widget.getShell().setVisible(true);
//				 widget.getShell().open();
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
	public IUimWidget getWidget(){
		return widget;
	}
	@Override
	public void setLabelText(String string){
		fFigureGridPanelNameFigure.setText(string);
	}
}