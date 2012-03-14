package org.opaeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uimodeler.common.figures.IUimFieldFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

public class CustomFieldColumnFigure extends RectangleFigure implements IUimFieldFigure{
	private WrappingLabel fColumnNameFigure;
	private Composite composite;
	private Control control;
	private TableColumn column;
	public CustomFieldColumnFigure(Composite comp){
		createContents();
		Table table = ((UimDataTableComposite) comp).getTable();
		column = new TableColumn(table, SWT.LEFT);
		Composite firstRow = (Composite) comp.getChildren()[1];
		this.composite = new Composite(firstRow, SWT.NONE){
			@Override
			public void setData(String key,Object data){
				super.setData(key, data);
				column.setData(key, data);
			}
			@Override
			public void dispose(){
				super.dispose();
				column.dispose();
			}
		};
		getComposite().setData(UimFigureUtil.FIGURE, this);
		GridLayout rl = new GridLayout(1, false);
		rl.marginWidth = 0;
		rl.marginHeight = 0;
		getComposite().setLayout(rl);
		setControl(createDefaultControl());
		getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	protected Control createDefaultControl(){
		return new Text(getComposite(), SWT.NONE);
	}
	private void createContents(){
		fColumnNameFigure = new WrappingLabel();
		fColumnNameFigure.setText("<..>");
		this.add(fColumnNameFigure);
	}
	public WrappingLabel getColumnNameFigure(){
		return fColumnNameFigure;
	}
	public void paint(Graphics graphics){
		Point copy = ((Figure) getParent()).getLocation().getCopy();
		graphics.drawImage((Image) getWidget().getData("OPAEUM_IMAGE"), copy.x + 1, copy.y);
	}
	@Override
	public Widget getWidget(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
		column.setText(string);
		((UimDataTableComposite) column.getParent().getParent()).recalculateColumns();
	}
	@Override
	public void setMinimumLabelWidth(Integer minimumLabelWidth){
		// TODO Auto-generated method stub
	}
	@Override
	public Composite getComposite(){
		return composite;
	}
	@Override
	public void setMinimumLabelHeigh(Integer newValue){
	}
	@Override
	public void setControl(Control button){
		this.control = button;
	}
	@Override
	public Control getControl(){
		return control;
	}
	@Override
	public void markForRepaint(){
		column.getParent().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		column.getParent().layout();
		UimDataTableComposite dtc = (UimDataTableComposite) column.getParent().getParent();
		Table t = dtc.getTable();
		t.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		dtc.recalculateColumns();
	}
}