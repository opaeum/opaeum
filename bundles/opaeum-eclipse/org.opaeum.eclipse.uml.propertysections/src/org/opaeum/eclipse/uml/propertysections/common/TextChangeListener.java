package org.opaeum.eclipse.uml.propertysections.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public abstract class TextChangeListener implements Listener{
	private boolean nonUserChange;
	private String lastUpdatedText = "";
	public void startNonUserChange(){
		if(nonUserChange){
			throw new IllegalStateException("we already started a non user change");//$NON-NLS-1$
		}
		nonUserChange = true;
	}
	public void finishNonUserChange(){
		if(!nonUserChange){
			throw new IllegalStateException("we are not in a non user change");//$NON-NLS-1$
		}
		nonUserChange = false;
	}
	public boolean isNonUserChange(){
		return nonUserChange;
	}
	public void handleEvent(Event event){
		switch(event.type){
		case SWT.KeyDown:
			if(event.character == SWT.CR){
				internalTextChanged((Control) event.widget);
			}
			break;
		case SWT.FocusOut:
			focusOut((Control) event.widget);
			internalTextChanged((Control) event.widget);
			break;
		case SWT.FocusIn:
			focusIn((Control) event.widget);
			break;
		default:
			break;
		}
	}
	private void internalTextChanged(Control control){
		if(!lastUpdatedText.equals(getText(control))){
			lastUpdatedText=getText(control);
			textChanged(control);
		}
	}
	public abstract void textChanged(Control control);
	public void focusIn(Control control){
	}
	public void focusOut(Control control){
	}
	public void startListeningTo(Control control){
		lastUpdatedText = getText(control);
		control.addListener(SWT.FocusIn, this);
		control.addListener(SWT.FocusOut, this);
		control.addListener(SWT.Modify, this);
	}
	private String getText(Control control){
		if(control instanceof Text){
			return ((Text) control).getText();
		}else if(control instanceof StyledText){
			return ((StyledText) control).getText();
		}else{
			throw new IllegalStateException("Unsupported Control : " + control.getClass());
		}
	}
	public void startListeningForEnter(Control control){
		// NOTE: KeyDown rather than KeyUp, because of similar usage in CCombo.
		control.addListener(SWT.KeyDown, this);
		lastUpdatedText = getText(control);
	}
	public void stopListeningTo(Control control){
		if((control != null) && !control.isDisposed()){
			control.removeListener(SWT.FocusOut, this);
			control.removeListener(SWT.Modify, this);
			control.removeListener(SWT.KeyDown, this);
		}
	}
}