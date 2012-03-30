package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class NumberScroller extends Composite{
	Button down,up;
	Text text;
	public NumberScroller(Composite parent,int style){
		super(parent, style);
		createText();
	}
	void createText(){
		text = new Text(this, SWT.SINGLE);
		text.setText("123456789");
		
		up = new Button(this, SWT.ARROW | SWT.UP);
		down = new Button(this, SWT.ARROW | SWT.DOWN);
		addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				onResize(event);
			}
		});
		setSize(text.getSize().x+50, 20);
	}
	void onResize(Event event){
		Rectangle rect = getClientArea();
		int width = rect.width;
		int height = rect.height;
		Point buttonSize = down.computeSize(SWT.DEFAULT, height);
		text.setBounds(0, 0, width - buttonSize.x, height);
		int buttonHeight = height / 2;
		up.setBounds(width - buttonSize.x, 0, buttonSize.x, buttonHeight);
		down.setBounds(width - buttonSize.x, buttonHeight, buttonSize.x, buttonHeight);
	}
}