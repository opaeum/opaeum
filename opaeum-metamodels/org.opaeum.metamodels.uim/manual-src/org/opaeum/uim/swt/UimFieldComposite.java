package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UimFieldComposite extends Composite implements IUimFieldComposite{
	private Label label;
	private Control control;
	public UimFieldComposite(Composite parent,int style){
		super(parent, style | SWT.BORDER);
		GridLayout rl = new GridLayout(2, false);
		rl.marginWidth = 0;
		rl.marginHeight = 0;
		rl.horizontalSpacing = 0;
		setLayout(rl);
		setLabel(new Label(this, SWT.NONE));
		setControl(createDefaultControl());
		getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		GridData labelData = new GridData(GridData.CENTER, GridData.FILL, false, false);
		getLabel().setLayoutData(labelData);
	}
	@Override
	public Control getControl(){
		return control;
	}
	protected Control createDefaultControl(){
		Text text = new Text(this, SWT.NONE);
		return text;
	}
	@Override
	public void setControl(Control control){
		this.control = control;
	}
	@Override
	public Label getLabel(){
		return label;
	}
	public void setLabel(Label label){
		this.label = label;
	}
	@Override
	public void setLayout(GridLayout layout){
		super.setLayout(layout);
	}
	public void setMinimumLabelWidth(Integer minimumLabelWidth){
		GridData gd = (GridData) getLabel().getLayoutData();
		gd.minimumWidth = minimumLabelWidth == null ? 150 : minimumLabelWidth;
	}
}