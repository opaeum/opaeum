package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.os.OSSupport;

public abstract class CustomUimActionFigure extends RectangleFigure implements ISWTFigure{
	protected Button button;
	protected abstract void createContents();
	public CustomUimActionFigure(Composite parent){
		FlowLayout layoutThis = new FlowLayout();
		layoutThis.setStretchMinorAxis(false);
		layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layoutThis.setMajorSpacing(5);
		layoutThis.setMinorSpacing(5);
		layoutThis.setHorizontal(true);
		this.setLayoutManager(layoutThis);
		createContents();
		button = new Button(parent, SWT.PUSH);
	}
	protected void paintClientArea(Graphics graphics){
		Point copy;
		if(getParent() instanceof HackedDefaultSizeNodeFigure){
			copy = ((Figure) getParent()).getLocation().getCopy();
		}else{
			copy = getLocation().getCopy();
		}
		try{
			graphics.drawImage((Image) getButton().getData("OPAEUM_IMAGE"), copy.x, copy.y);
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Button getButton(){
		return button;
	}
	@Override
	public Control getWidget(){
		return button;
	}
	@Override
	public void markForRepaint(){
		Composite grandpa = button.getParent().getParent();
		if(grandpa instanceof UimDataTableComposite){
			UimDataTableComposite dtc = (UimDataTableComposite)grandpa;
			Table t = dtc.getTable();
			t.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			dtc.recalculateColumns();			
		}
	}
	public void setLabelText(String string){
		button.setText(string);
	}
}