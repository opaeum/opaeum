package org.opaeum.uim.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class CSingleObjectChooser extends Composite{
	private Text field;
	private Button chooseBt;
	public CSingleObjectChooser(Composite parent,int style){
		super(parent, style);
		createContents(this);
	}
	protected void createContents(Composite parent){
		setLayout(parent);
		field = new Text(parent, SWT.FLAT | SWT.BORDER | SWT.READ_ONLY);
		field.setText("");
		field.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		chooseBt = new Button(parent, SWT.PUSH);
		chooseBt.setText("...");
	}
	private void setLayout(Composite parent){
		int numColumns = 2;
		if(numColumns < 2){
			numColumns = 2;
		}
		GridLayout layout = new GridLayout(numColumns, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
	}
	public void setText(String text){
		field.setText(text);
	}
}
