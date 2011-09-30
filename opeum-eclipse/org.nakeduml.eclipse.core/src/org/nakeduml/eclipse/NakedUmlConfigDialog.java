package org.opeum.eclipse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

import net.sf.opeum.feature.ISourceFolderStrategy;
import net.sf.opeum.feature.ITransformationStep;
import net.sf.opeum.feature.NakedUmlConfig;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.opeum.name.NameConverter;

public class NakedUmlConfigDialog extends TitleAreaDialog{
	private Text txtWorkspaceName;
	private Text txtWorkspaceIdentifier;
	private Text txtCompanyDomain;
	private Button chkGeneratePoms;
	private CCombo cboSourceFolderStrategy;
	private List lstTransformationSteps;
	private NakedUmlConfig config;
	public NakedUmlConfigDialog(Shell shell,NakedUmlConfig config){
		super(shell);
		this.config = config;
	}
	protected Control createContents(Composite parent){
		Control contents = super.createContents(parent);
		setTitle("NakedUml Model Compiler");
		setMessage("Please provide input", IMessageProvider.NONE);
		// Set the image
		return contents;
	}
	protected Control createDialogArea(Composite parent){
		Composite composite = (Composite) super.createDialogArea(parent);
		Composite panel = new Composite(composite, 0);
		panel.setLayout(new GridLayout(2, true));
		new Label(panel, 0).setText("Project Name");
		txtWorkspaceName = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtWorkspaceName.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceName.setText(config.getWorkspaceName());
		new Label(panel, 0).setText("Identifier for project");
		txtWorkspaceIdentifier = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtWorkspaceIdentifier.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceIdentifier.setText(config.getWorkspaceIdentifier());
		new Label(panel, 0).setText("Company domain name");
		txtCompanyDomain = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtCompanyDomain.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtCompanyDomain.setText(getDomainName());
		new Label(panel, 0).setText("Generate Maven POMS");
		chkGeneratePoms = new Button(panel, SWT.CHECK);
		chkGeneratePoms.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		chkGeneratePoms.setSelection(config.generateMavenPoms());
		new Label(panel, 0).setText("Source Folder Strategy");
		cboSourceFolderStrategy = new CCombo(panel, SWT.BORDER);
		cboSourceFolderStrategy.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		for(Class<? extends ISourceFolderStrategy> s:NakedUmlEclipsePlugin.getDefault().getSourceFolderStrategies()){
			cboSourceFolderStrategy.add(s.getName());
		}
		cboSourceFolderStrategy.setText(config.getSourceFolderStrategy().getClass().getName());
		new Label(panel, 0).setText("Additional Transformation Steps");
		lstTransformationSteps = new List(panel, SWT.MULTI | SWT.BORDER);
		lstTransformationSteps.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		for(Class<? extends ITransformationStep> t:NakedUmlEclipsePlugin.getDefault().getTransformationSteps()){
			lstTransformationSteps.add(t.getName());
		}
		String[] items = lstTransformationSteps.getItems();
		for(int i = 0;i < items.length;i++){
			if(config.getAdditionalTransformationSteps().contains(NakedUmlConfig.getClass(items[i]))){
				lstTransformationSteps.select(i);
			}
		}
		return composite;
	}
	private String getDomainName(){
		if(config.getMavenGroupId().length() == 0){
			return "acme.com";
		}else{
			StringBuilder sb = new StringBuilder();
			String[] split = config.getMavenGroupId().split("\\.");
			for(int i = split.length - 2;i >= 0;i--){
				sb.append(split[i]);
				if(i != 0){
					sb.append(".");
				}
			}
			return sb.toString();
		}
	}
	public void okPressed(){
		config.loadDefaults(txtWorkspaceIdentifier.getText());
		String domain = txtCompanyDomain.getText();
		StringBuilder mavenGroup = null;
		StringTokenizer st = new StringTokenizer(domain, ".");
		while(st.hasMoreTokens()){
			if(mavenGroup == null){
				mavenGroup = new StringBuilder(st.nextToken());
			}else{
				mavenGroup.insert(0, '.');
				mavenGroup.insert(0, st.nextToken());
			}
		}
		mavenGroup.append('.');
		mavenGroup.append(NameConverter.separateWordsToCamelCase(txtWorkspaceName.getText()).toLowerCase());
		config.setWorkspaceName(NameConverter.separateWordsToCamelCase(txtWorkspaceName.getText()));
		config.setAdditionalTransformationSteps(new HashSet<String>(Arrays.asList(lstTransformationSteps.getSelection())));
		config.setMavenGroupId(mavenGroup.toString());
		config.setSourceFolderStrategy(cboSourceFolderStrategy.getText());
		config.setWorkspaceIdentifier(txtWorkspaceIdentifier.getText());
		config.setGenerateMavenPoms(this.chkGeneratePoms.getSelection());
		config.store();
		super.okPressed();
	}
	protected void createButtonsForButtonBar(Composite parent){
		Button ok = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}
	public NakedUmlConfig getConfig(){
		return config;
	}
}
