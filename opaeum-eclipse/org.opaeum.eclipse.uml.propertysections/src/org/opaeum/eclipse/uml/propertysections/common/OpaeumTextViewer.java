package org.opaeum.eclipse.uml.propertysections.common;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class OpaeumTextViewer extends TextViewer implements IText{
	public OpaeumTextViewer(){
		super();
	}
	public OpaeumTextViewer(Composite parent,int styles){
		super(parent, styles | SWT.BORDER | SWT.FLAT);
	}
	@Override
	public void setEnabled(boolean enabled){
		if(getTextWidget() != null){
			getTextWidget().setEnabled(enabled);
		}
	}
	@Override
	public void setLayoutData(Object layoutData){
		getTextWidget().setLayoutData(layoutData);
		if(layoutData instanceof FormData){
			FormData fd=(FormData) layoutData;
			fd.height=25;
		}
	}
	@Override
	public void setText(String string){
		if(getTextWidget() != null){
			getTextWidget().setText(string);
		}
	}
	@Override
	public String getText(){
		return getTextWidget().getText();
	}
	@Override
	public void setBackground(Color color){
		getTextWidget().setBackground(color);
	}
	@Override
	public void setForeground(Color color){
		getTextWidget().setForeground(color);
	}
	@Override
	public Control getTextControl(){
		return getTextWidget();
	}
}