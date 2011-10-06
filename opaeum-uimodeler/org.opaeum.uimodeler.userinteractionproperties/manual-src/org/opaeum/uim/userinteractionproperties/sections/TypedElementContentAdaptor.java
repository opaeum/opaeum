package org.opeum.uim.userinteractionproperties.sections;

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public final class TypedElementContentAdaptor extends TextContentAdapter{
	@Override
	public void setControlContents(Control control,String text,int cursorPosition){
		super.setControlContents(control, text, cursorPosition);
	}
	@Override
	public void insertControlContents(Control control,String text,int cursorPosition){
		Text txtCtrl=(Text) control;
		String curValue = txtCtrl.getText();
		curValue=curValue.substring(0,curValue.lastIndexOf(".")+1);
		String newValue = curValue + text;
		txtCtrl.setText(newValue);
		setCursorPosition(control, newValue.length());
	}
	@Override
	public void setCursorPosition(Control control,int position){
		super.setCursorPosition(control, position);
	}
	@Override
	public void setSelection(Control control,Point range){
		super.setSelection(control, range);
	}
}