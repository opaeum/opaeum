package org.opeum.uim.userinteractionproperties.sections;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class TextControlAdapter extends Composite implements IText{
	Text text;
	boolean ctrlDown;
	public TextControlAdapter(Composite parent,int style){
		super(parent,style);
        this.setLayout(new FillLayout());
		text = new Text(this, SWT.BORDER);
		text.addKeyListener(new KeyListener(){
			@Override
			public void keyReleased(KeyEvent event){
			}
			@Override
			public void keyPressed(KeyEvent event){
			}
		});
		text.addListener(SWT.KeyDown, new Listener(){
			@Override
			public void handleEvent(Event event){
				try{
					if(KeyStroke.getInstance("CTRL+SPACE").getModifierKeys()==event.keyCode){
						ctrlDown=true;
						event.stateMask= 0XFFFFFFFF;
						event.keyCode=' ';
					}
					// TODO Auto-generated method stub
				}catch(Exception e){
				}
			}
		});
		text.addListener(SWT.KeyUp, new Listener(){
			@Override
			public void handleEvent(Event event){
				try{
					if(KeyStroke.getInstance("CTRL+SPACE").getModifierKeys()==event.keyCode){
						ctrlDown=false;
//						event.stateMask = 0XFFFFFFFF;
					}
					// TODO Auto-generated method stub
				}catch(Exception e){
				}
			}
		});
	}
	public Control getTextControl(){
		return text;
	}
	@Override
	public Control getControl(){
		return this;
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
		super.setLayoutData(layoutData);
	}
	@Override
	public void setText(String string){
		if(text instanceof Text){
			((Text) text).setText(string);
		}else{
			text.setToolTipText(string);
		}
	}
	@Override
	public String getText(){
		if(text instanceof Text){
			return ((Text) text).getText();
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