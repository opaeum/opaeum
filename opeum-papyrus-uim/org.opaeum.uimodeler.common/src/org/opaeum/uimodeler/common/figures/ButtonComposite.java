package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uim.swt.UimSwtUtil;

public class ButtonComposite extends Composite implements IUimWidget{
	Button button;
	public ButtonComposite(Composite parent,int style){
		super(parent, style);
		StackLayout sl = new StackLayout();
		sl.marginWidth=0;
		sl.marginHeight=0;
		setLayout(sl);
		button=new Button(this, SWT.PUSH);
		sl.topControl=button;
	}

	@Override
	public void markForShot(){
		setData(UimSwtUtil.WBP_NEED_IMAGE,Boolean.TRUE);
	}
}
