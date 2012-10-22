package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ColumnComposite extends Composite implements IUimFieldComposite{
	private Control control;
	public ColumnComposite(Composite parent,int style){
		super(parent, style);
		GridLayout rl = new GridLayout(1, false);
		rl.marginWidth = 0;
		rl.marginHeight = 0;
		setLayout(rl);
		setControl(createDefaultControl());
		getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	protected Control createDefaultControl(){
		return new Text(this, SWT.NONE);
	}
	
	@Override
	public void setData(String key,Object value){
		super.setData(key, value);
	}
	@Override
	public Control getControl(){
		return control;
	}
	@Override
	public void setControl(Control control){
		this.control=control;
	}
	@Override
	public Label getLabel(){
		return null;
	}
	@Override
	public void setLayout(GridLayout layout){
		super.setLayout(layout);
	}
	@Override
	public void setMinimumLabelWidth(Integer minimumLabelWidth){
		// TODO Auto-generated method stub
		
	}
	@Override
	public void markForShot(){
		setData(UimSwtUtil.WBP_NEED_IMAGE,Boolean.TRUE);
	}
}
