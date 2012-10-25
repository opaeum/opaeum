package org.opaeum.eclipse.uml.propertysections.standardprofile;

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
	public void insertControlContents(Control control,String text,int position){
		Text txtCtrl=(Text) control;
		String curValue = txtCtrl.getText();
		int i = 0;
		int curPosition = curValue.length()+text.length()-position;
		for(i = curPosition;i > 0;i--){
			if(!Character.isJavaIdentifierPart(curValue.charAt(i-1))){
				break;
			}
		}
		String pre=curValue.substring(0,i);//Include operator character
		String post = curValue.substring(curPosition);
		txtCtrl.setText(pre+text+post);
		setCursorPosition(control, (pre+text).length());
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