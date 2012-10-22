package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.dnd.SwtUtil;

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
	public void setEnabled(boolean value){
		super.setEnabled(value);
		Control[] children = getChildren();
		setEnabled(value, children);
	}
	private void setEnabled(boolean value,Control[] children){
		for(Control control:children){
			control.setEnabled(value);
			if(control instanceof Composite){
				setEnabled(value,((Composite) control).getChildren());
			}
		}
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
		GridData labelData = (GridData) getLabel().getLayoutData();
		if(minimumLabelWidth == null){
			labelData.minimumWidth = 150;
			labelData.widthHint=150;
		}else{
			labelData.minimumWidth = minimumLabelWidth;
			labelData.widthHint=minimumLabelWidth;
		}
	}
	@Override
	public void markForShot(){
		super.setData(UimSwtUtil.WBP_NEED_IMAGE, Boolean.TRUE);
	}
}
