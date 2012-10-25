package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uimodeler.common.UimFigureUtil;
import org.opaeum.uimodeler.common.figures.ISWTFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;
import org.opaeum.uimodeler.common.figures.WindowBuilderUtil;

/**
 * @generated NOT
 */
public class CustomUimDataTableFigure extends RectangleFigure implements ISWTFigure{
	/**
	 * @generated NOT
	 */
	private WrappingLabel fUimDataTableNameFigure;
	/**
	 * @generated
	 */
	public CustomUimDataTableFigure(){
		createContents();
	}
	UimDataTableComposite composite;
	/**
	 * @generated NOT
	 */
	public CustomUimDataTableFigure(Composite nearestComposit){
		createContents();
		this.composite = new UimDataTableComposite(nearestComposit, SWT.BORDER);
		this.composite.setData(UimFigureUtil.FIGURE, this);
		setOpaque(false);
	}
	@Override
	public Rectangle getBounds(){
		return super.getBounds();
	}
	@Override
	public void setBounds(Rectangle rect){
		super.setBounds(rect);
		// this.composite.recalculateColumns();
	};
	protected void layout(){
		IFigure parent = getParent();
		Rectangle fUimDataTableNameFigureBounds = fUimDataTableNameFigure.getBounds();
		fUimDataTableNameFigureBounds.height = 0;
		fUimDataTableNameFigureBounds.width = 0;
		fUimDataTableNameFigure.setBounds(fUimDataTableNameFigureBounds);
		// NB!! These bounds will be exactly the same as the block in the containing grid layout see HackedDEfaultSizeNodeFigure
		// HACK for some reason we have tiny bounds when layout takes place, but on painting we have the correct bounds- may have something to
		// do with the way the gridpanel works.
		Rectangle bnds = parent.getBounds();
		// Convert back to the correct bounds
		Figure columnCompartment = (Figure) getChildren().get(0);
		// Rectangle firstRowBounds = UimFigureUtil.toDraw2DRectangle(composite.getFirstRow());
		// columnCompartment.setBounds(firstRowBounds);
		Rectangle columnCompartmentBounds = new Rectangle();
		columnCompartmentBounds.x = bnds.x - 4;
		columnCompartmentBounds.y = this.composite.getHeaderHeight() + 1;
		columnCompartmentBounds.width = bnds.width + 10;
		columnCompartmentBounds.height = bnds.height - (UimDataTableComposite.ROW_HEIGHT + this.composite.getHeaderHeight());
		columnCompartment.setBounds(columnCompartmentBounds);
		if(getChildren().size() >= 2){
			ShapeCompartmentFigure actionBarCompartment = (ShapeCompartmentFigure) getChildren().get(1);
			// actionBarCompartment.setBounds(UimFigureUtil.toDraw2DRectangle(composite.getActionBar()));
			Rectangle actionBarCompartmentBounds = new Rectangle();
			actionBarCompartmentBounds.x = columnCompartmentBounds.x;
			actionBarCompartmentBounds.y = columnCompartmentBounds.y + columnCompartmentBounds.height+2;
			actionBarCompartmentBounds.width = columnCompartmentBounds.width;
			actionBarCompartmentBounds.height = UimDataTableComposite.ROW_HEIGHT + 5;
			actionBarCompartment.setBackgroundColor(ColorConstants.red);
			actionBarCompartment.setBounds(actionBarCompartmentBounds);
		}
		// this.composite.recalculateColumns();
	}
	@Override
	protected void paintClientArea(Graphics graphics){
		try{
			// if(WindowBuilderUtil.needsComponentShot(composite)){
			long start = System.currentTimeMillis();
			WindowBuilderUtil.revealSecondLevel(composite.getFirstRow());
			if(WindowBuilderUtil.needsComponentShot(composite.getFirstRow())){
				System.out.println("CustomUimDataTableFigure.paintClientArea() makeShot");
				OSSupport.get().beginShot(composite.getFirstRow());
				OSSupport.get().makeShots(composite.getFirstRow());
				OSSupport.get().endShot(composite.getFirstRow());
				WindowBuilderUtil.clearNeedsImage(composite);
				WindowBuilderUtil.hideSecondLevel(composite.getFirstRow());
				System.out.println("Shot took " + (System.currentTimeMillis() - start));
			}
			graphics.drawImage( (Image) composite.getTable().getData(UimFigureUtil.OPAEUM_IMAGE), 6, 6);
			super.paintClientArea(graphics);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @generated NOT
	 */
	private void createContents(){
		fUimDataTableNameFigure = new WrappingLabel();
		fUimDataTableNameFigure.setText("...");
		this.add(fUimDataTableNameFigure);
	}
	/**
	 * @generated NOT
	 */
	public WrappingLabel getUimDataTableNameFigure(){
		return fUimDataTableNameFigure;
	}
	@Override
	public IUimWidget getWidget(){
		return composite;
	}
	public UimDataTableComposite getComposite(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
	}
}