package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;

public class LinkComposite extends Composite{

	public LinkComposite(Composite parent,int style){
		super(parent, style|SWT.BORDER);
		StackLayout layout = new StackLayout();
		setLayout(layout);
		Link link = new Link(this,SWT.NONE);
		link.setText("<Object Name>");
		link.setForeground(ColorConstants.blue);
		layout.topControl=link;
		layout();
	}
	public LinkComposite(Composite parent,int style,boolean many){
		super(parent, style|SWT.BORDER);
		setLayout(new GridLayout(1,false));
		Link link = new Link(this,SWT.NONE);
		link.setText("<Object1 Name>");
		link.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, true));
		link = new Link(this,SWT.NONE);
		link.setText("<Object2 Name>");
		link.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, true));
		link = new Link(this,SWT.NONE);
		link.setText("<Object3 Name>");
		link.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, true));
		link = new Link(this,SWT.NONE);
		link.setText("<Object4 Name>");
		link.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, true));
	}
}
