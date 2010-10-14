/*
 * Created on Apr 25, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.ui.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * RadioGroup : 
 */
public class RadioGroup {
	private static final int DEFAULT_TEXT_STYLE  = SWT.NONE;
	private static final int DEFAULT_LABEL_STYLE = SWT.NONE;
	private static final int DEFAULT_ID_WIDTH    = 250;
	private static final int DEFAULT_TEXT_WIDTH  = 250;
	private static final int DEFAULT_TABLE_WIDTH = 250;
	private Label  label = null;
	private Button button = null;
	private String title = "Octopus";
	
	/**
	 * 
	 */
	public RadioGroup(Composite parent, IProject currJProject, String name, String dialogTitle) {
		super();
		button = new Button(parent, SWT.RADIO);
		button.addSelectionListener(new Show(this, currJProject));
		
		label = new Label(parent, DEFAULT_LABEL_STYLE);
		label.setText(name);

		this.title = dialogTitle;
	}

	public void setValue(boolean value) {
		button.setSelection(value);
	}
 
	public boolean getValue() {
		return button.getSelection();
	}
	
	class Show implements SelectionListener {
			
		private RadioGroup parent;
		private IProject fCurrJProject;
			
		public Show(RadioGroup parent, IProject currJProject) {
			super();
			this.parent = parent;
			this.fCurrJProject = currJProject;
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			System.out.println("BUTTON PUSH");
		}
		
		public void widgetSelected(SelectionEvent e) {
		}

	}
	

}
