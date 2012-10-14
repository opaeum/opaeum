package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class TextControlAdapter {
	Text text;
	boolean ctrlDown;
	public TextControlAdapter(Text text, ITypedElementProvider s){
		super();
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
		// "." and "#" will also activate the content proposals
		char[] autoActivationCharacters = new char[]{
			'.'
		};
		KeyStroke keyStroke;
		try{
			keyStroke = KeyStroke.getInstance("CTRL+SPACE");
			// assume that myTextControl has already been created in some way
			new ContentProposalAdapter(text, new TypedElementContentAdaptor(), new TypedElementContentProposalProvider(
					s), keyStroke, autoActivationCharacters);
		}catch(ParseException e){
			e.printStackTrace();
		}

	}

}