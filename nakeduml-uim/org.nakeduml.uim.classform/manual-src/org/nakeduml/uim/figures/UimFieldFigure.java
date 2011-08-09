package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.nakeduml.uim.figures.controls.IControlFigure;
import org.nakeduml.uim.figures.controls.UimTextFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UimFieldFigure extends Figure implements ILabelFigure,IBindingFigure{
	private EditableLabel label;
	private IControlFigure control;
	private int labelWidth;
	private boolean vertical;
	public UimFieldFigure(){
		this(2);
	}
	protected UimFieldFigure(int noOfColumns){
		super();
		getInsets().bottom = 0;
		getInsets().top = 0;
		getInsets().left = 0;
		getInsets().right = 0;
		label = new EditableLabel(){
			@Override
			public void setFont(Font f){
				super.setFont(f);
				control.setFont(f);
			}
		};
		GridLayout gl = new GridLayout();
		gl.makeColumnsEqualWidth = false;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		setLayoutManager(gl);
		add(this.label, new GridData());
		add(this.control = new UimTextFigure("Select Property"), new GridData());
	}
	public EditableLabel getLabel(){
		return label;
	}
	public IControlFigure getControl(){
		return control;
	}
	public int getLabelWidth(){
		return labelWidth;
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		Rectangle r = (Rectangle) getParent().getLayoutManager().getConstraint(this);
		if(r != null){
			r.width = labelWidth;
		}
	}
	@Override
	public ILabel getBindingLabel(){
		return getControl().getBindingLabel();
	}
	@Override
	public void setFont(Font f){
		super.setFont(f);
	}
	public GridLayout getGridLayout(){
		return (GridLayout) getLayoutManager();
	}
	public void setControl(IControlFigure control){
		if(this.control != null){
			remove(this.control);
		}
		boolean vertical = getFont() != null && control.getMinimumSize().height > 50;
		control.setFont(getFont());
		add(this.control = control, new GridData());
		setVertical(vertical);
		layout();
	}
	public void setVertical(boolean vertical){
		this.vertical = vertical || getParent() instanceof ColumnFigure;
	}
	public void layout(){
		GridData controlGridData = (GridData) getLayoutManager().getConstraint(this.control);
		GridData labelGridData = (GridData) getLayoutManager().getConstraint(this.label);
		if(vertical){
			((GridLayout) getLayoutManager()).numColumns = 1;
			labelGridData.heightHint = 30;
			labelGridData.widthHint = getSize().width;
			controlGridData.widthHint = getSize().width;
			controlGridData.heightHint = getSize().height - labelGridData.heightHint;
			controlGridData.grabExcessHorizontalSpace = true;
		}else{
			((GridLayout) getLayoutManager()).numColumns = 2;
			labelGridData.heightHint = getSize().height;
			labelGridData.widthHint = getLabelWidth();
			controlGridData.heightHint = getSize().height;
			controlGridData.widthHint = getSize().width - getLabelWidth();
			controlGridData.grabExcessHorizontalSpace = true;
		}
		super.layout();
	}
	public void paint(final Graphics g){
		super.paint(g);
	}
	private void paintTree(final Graphics g){
	}
}
