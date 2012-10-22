package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uim.swt.UimSwtUtil;

public class LinkComposite extends Composite implements IUimWidget{
	Label label;
	public LinkComposite(Composite parent,int style){
		super(parent, style);
		StackLayout sl = new StackLayout();
		sl.marginWidth=0;
		sl.marginHeight=0;
		setLayout(sl);
		label=new Label(this, SWT.NONE);
		sl.topControl=label;
	}

	@Override
	public void markForShot(){
		setData(UimSwtUtil.WBP_NEED_IMAGE, Boolean.TRUE);
	}

	public void setText(String string){
		label.setText(string);
	}

	public void setImage(Image imageFor){
		label.setImage(imageFor);
	}
}
