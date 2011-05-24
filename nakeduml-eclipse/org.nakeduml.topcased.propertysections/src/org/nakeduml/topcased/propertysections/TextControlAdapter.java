package org.nakeduml.topcased.propertysections;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class TextControlAdapter implements IText{
	Control text;
	public TextControlAdapter(Composite parent,int style){
		text = new Text(parent, style);
	}
	public TextControlAdapter(Control c){
		this.text = c;
	}
	public Control getTextControl(){
		return text;
	}
	@Override
	public Control getControl(){
		return text;
	}
	@Override
	public void setEditable(boolean isChangeable){
		if(text instanceof Text){
			((Text) text).setEditable(isChangeable);
		}
	}
	@Override
	public void setEnabled(boolean isChangeable){
		text.setEnabled(isChangeable);
	}
	@Override
	public void setLayoutData(Object layoutData){
		text.setLayoutData(layoutData);
	}
	@Override
	public void setText(String string){
		if(text instanceof Text){
			text.setToolTipText(string);
		}
	}
	@Override
	public String getText(){
		if(text instanceof Text){
			return ((Text) text).getText();
		}else if(text instanceof Composite){
			Composite c = (Composite) text;
			for(Control control:c.getChildren()){
				if(control instanceof StyledText){
					return ((StyledText) control).getText();
				}
			}
		}
		return "";
	}
	@Override
	public void setBackground(Color color){
		text.setBackground(color);
	}
	@Override
	public void setForeground(Color color){
		text.setForeground(color);
	}
}