package org.opaeum.runtime.jface.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class MessageLink extends Composite{
	private Link link;
	private Label image;
	public MessageLink(Composite parent,int style){
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		this.image = new Label(this, SWT.NONE);
		this.link = new Link(this, SWT.NONE);
	}
	public Link getLink(){
		return link;
	}
	public Label getImageLabel(){
		return image;
	}
	public void setText(String message){
		link.setText("<a href=\"www.google.com\">"+message+"</a>");
		// TODO Auto-generated method stub
		
	}
}
