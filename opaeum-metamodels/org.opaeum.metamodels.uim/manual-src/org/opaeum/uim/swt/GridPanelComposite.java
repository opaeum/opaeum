package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class GridPanelComposite extends Composite{
	Label label;
	Composite contentPane;
	public GridPanelComposite(Composite parent,int style){
		super(parent, style | SWT.BORDER | SWT.SHADOW_ETCHED_OUT);
		super.setLayout(new GridLayout(1, false));
		label=new Label(this,SWT.NONE);
		GridData labelGd = new GridData(100, 20);
		labelGd.grabExcessHorizontalSpace=true;
		label.setLayoutData(labelGd);
		contentPane = new Composite(this, SWT.NONE);
		contentPane.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		contentPane.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(200, 40);
		gd.minimumHeight = 20;
		gd.minimumWidth = 60;
		setLayoutData(gd);
		double r = Math.random();
//		if(r < 0.2){
//			setBackground(ColorConstants.blue);
//		}else if(r < 0.4){
//			setBackground(ColorConstants.cyan);
//		}else if(r < 0.6){
//			setBackground(ColorConstants.yellow);
//		}else if(r < 0.8){
//			setBackground(ColorConstants.darkGreen);
//		}else{
//			setBackground(ColorConstants.red);
//		}
		layout();
	}
	public Label getLabel(){
		return label;
	}
	public Composite getContentPane(){
		return contentPane;
	}
	@Override
	public void setLayout(Layout layout){
	}
}
