package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
		layout.horizontalSpacing = 1;
		layout.verticalSpacing = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		createText();
	}
	void createText(){
		text = new Text(this, SWT.SINGLE);
		text.setText("123456789");
		GridData dt = new GridData();
		text.setLayoutData(dt);
		dt.grabExcessHorizontalSpace = true;
		dt.horizontalAlignment=GridData.FILL;
		dt.verticalSpan = 2;
		up = new Button(this, SWT.PUSH);
		up.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				try{
					text.setText(""+(Integer.valueOf(text.getText())+1));
					text.redraw();
				}catch(Exception ex){
					text.setText("0");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		up.setLayoutData(new GridData(10, 10));
		up.setText("\u25B2");
		down = new Button(this, SWT.PUSH);
		down.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				try{
					text.setText(""+(Integer.valueOf(text.getText())-1));
					text.redraw();
				}catch(Exception ex){
					text.setText("0");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		down.setText("\u25BC");
		down.getFont().getFontData()[0].setHeight(3);
		up.getFont().getFontData()[0].setHeight(3);
		down.setLayoutData(new GridData(10, 10));
		layout();
		addListener(SWT.Resize, new Listener(){
			public void handleEvent(Event event){
				// onResize(event);
			}
		});
		// setSize(text.getSize().x+50, 20);
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