/*
 * Created on Feb 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package nl.klasse.octopus.ui.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author jos
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DirField {
	private static final int DEFAULT_TEXT_STYLE  = SWT.NONE;
	private static final int DEFAULT_LABEL_STYLE = SWT.NONE;
	private static final int DEFAULT_ID_WIDTH    = 250;
	private static final int DEFAULT_TEXT_WIDTH  = 250;
	private static final int DEFAULT_TABLE_WIDTH = 250;
	private Label  label = null;
	private Text   text  = null;
	
	/**
	 * 
	 */
	public DirField(Composite parent, String name) {
		super();
		label = new Label(parent, DEFAULT_LABEL_STYLE);
		label.setText(name);

		text  = new Text (parent, SWT.SINGLE | SWT.BORDER );
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.widthHint = DEFAULT_ID_WIDTH;
//		gd.widthHint = convertWidthInCharsToPixels(TEXT_FIELD_WIDTH);
		gd.horizontalAlignment = GridData.FILL;
		text.setLayoutData(gd);

		text.setLayoutData(newTextLayoutData());
	}

	private GridData newTextLayoutData() {
		GridData g = new GridData();
		g.grabExcessHorizontalSpace = true;
		g.horizontalSpan = 1;
		g.widthHint = DEFAULT_TEXT_WIDTH;
		return g;
	}

    public void setValue(String value) {
    	text.setText(value);
    }
 
	public String getValue() {
		return text.getText();
	}
	
}
