package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uimodeler.util.UimFigureUtil;
import org.opaeum.uimodeler.util.WindowBuilderUtil;

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
		this.composite.firstRow.setData(UimFigureUtil.FIGURE, this);
	}
	@Override
	public void setBounds(Rectangle rect){
		super.setBounds(rect);
		// Here because the GRidPanel will likely print the components before we get to painti
		composite.prepareForPaint(rect);
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
		Rectangle columnCompartmentBounds = new Rectangle();
		columnCompartmentBounds.x = 8;// TODO find out why - currently trial and error it fluctuates after every regen!!!!
		int tableHeaderHeight = 27;
		int actionBarHeight = 38;
		columnCompartmentBounds.y = tableHeaderHeight;// TODO find out why - currently trial and error
		columnCompartmentBounds.width = bnds.width + 16;
		columnCompartmentBounds.height = bnds.height - (tableHeaderHeight + actionBarHeight);
		columnCompartment.setBounds(columnCompartmentBounds);
		IFigure actionBarCompartment = (IFigure) getChildren().get(1);
		Rectangle actionBarCompartmentBounds = new Rectangle();
		actionBarCompartmentBounds.x = 8;// TODO why?? trial an error???!
		actionBarCompartmentBounds.y = tableHeaderHeight + columnCompartmentBounds.height;
		actionBarCompartmentBounds.width = bnds.width + 16;
		actionBarCompartmentBounds.height = actionBarHeight;
		actionBarCompartment.setBounds(actionBarCompartmentBounds);
		System.out.println("actionBarCompartmentBounds=" + actionBarCompartmentBounds);
	}
	@Override
	protected void paintClientArea(Graphics graphics){
		// Layout is called +- 20 times per redraw, with different values. Call it one last time to ensure the most recent values have been
		// applied TODO investigate why.
		// Layout is fairly inexpensive here, but this is not ideal
		layout();
		Rectangle bnds = getBounds();
		// Prepare as late as possible to avoid accidental resizing and the subsequent repainting
		try{
			if(WindowBuilderUtil.needsComponentShot(composite)){
				long start = System.currentTimeMillis();
				OSSupport.get().makeShots(composite);
				System.out.println("Shot took " + (System.currentTimeMillis() - start));
				WindowBuilderUtil.clearNeedsImage(composite);
			}
			graphics.drawImage((Image) composite.table.getData("OPAEUM_IMAGE"), 14, 12);
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
	public Control getWidget(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
	}
	public Table getTable(){
		return composite.table;
	}
	public Composite getFirstRow(){
		return composite.firstRow;
	}
	@Override
	public void markForRepaint(){
	}
}