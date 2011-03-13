package org.nakeduml.eclipse.starter;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NakedUmlConfigDialog extends TitleAreaDialog{
	Text txtWorkspaceIdentifier;
	Text txtCompanyDomain;
	String workspaceIdentifier;
	String companyDomain;
	public NakedUmlConfigDialog(Shell shell){
		super(shell);
	}
	protected Control createContents(Composite parent){
		Control contents = super.createContents(parent);
		setTitle("NakedUml Model Compiler");
		setMessage("Please provide input", IMessageProvider.INFORMATION);
		// Set the image
		return contents;
	}
	protected Control createDialogArea(Composite parent){
		Composite composite = (Composite) super.createDialogArea(parent);
		Composite panel = new Composite(composite, 0);
		panel.setLayout(new GridLayout(2, true));
		new Label(panel, 0).setText("Identifier for project");
		txtWorkspaceIdentifier = new Text(panel, SWT.SINGLE);
		new Label(panel, 0).setText("Company domain name");
		txtCompanyDomain = new Text(panel, SWT.SINGLE);
		
		return composite;
	}
	public String getWorkspaceIdentifier(){
		return workspaceIdentifier;
	}
	public String getCompanyDomain(){
		return companyDomain;
	}
	public void okPressed(){
		workspaceIdentifier = txtWorkspaceIdentifier.getText();
		companyDomain = txtCompanyDomain.getText();
		super.okPressed();
	}
	protected void createButtonsForButtonBar(Composite parent){
		Button ok = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}
}
