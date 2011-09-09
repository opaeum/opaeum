package org.nakeduml.eclipse;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.ISourceFolderStrategy;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
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
import org.nakeduml.name.NameConverter;

public class NakedUmlConfigDialog extends TitleAreaDialog{
	private Label errorMessage;
	private Text txtWorkspaceName;
	private Text txtWorkspaceIdentifier;
	private Text txtCompanyDomain;
	private Button chkGeneratePoms;
	private CCombo cboSourceFolderStrategy;
	private List lstTransformationSteps;
	private NakedUmlConfig config;
	private IFile file;
	public NakedUmlConfigDialog(Shell shell,IFile file2){
		super(shell);
		this.file=file2;
		if(!file2.exists()){
			config=new NakedUmlConfig(new File( file2.getParent().getLocation().toFile() ,"nakeduml.properties"));
			config.loadDefaults("ProjectXYZ");
		}else{
			this.config = new NakedUmlConfig(file2.getLocation().toFile());

		}
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
		txtWorkspaceName = new Text(panel, SWT.SINGLE|SWT.BORDER);
		txtWorkspaceName.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceName.setText(config.getWorkspaceName());
		new Label(panel, 0).setText("Identifier for project");
		txtWorkspaceIdentifier = new Text(panel, SWT.SINGLE|SWT.BORDER);
		txtWorkspaceIdentifier.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtWorkspaceIdentifier.setText(config.getWorkspaceIdentifier());
		new Label(panel, 0).setText("Company domain name");
		txtCompanyDomain = new Text(panel, SWT.SINGLE|SWT.BORDER);
		txtCompanyDomain.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtCompanyDomain.setText(getDomainName());
		new Label(panel, 0).setText("Generate Maven POMS");
		chkGeneratePoms = new Button(panel, SWT.CHECK);
		chkGeneratePoms.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		chkGeneratePoms.setSelection(config.generateMavenPoms());
		new Label(panel, 0).setText("Source Folder Strategy");
		cboSourceFolderStrategy = new CCombo(panel, SWT.BORDER);
		cboSourceFolderStrategy.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		for(ISourceFolderStrategy s:NakedUmlEclipsePlugin.getDefault().getSourceFolderStrategies()){
			cboSourceFolderStrategy.add(s.getClass().getName());
		}
		cboSourceFolderStrategy.setText(config.getSourceFolderStrategy().getClass().getName());
		new Label(panel, 0).setText("Additional Transformation Steps");
		lstTransformationSteps = new List(panel, SWT.MULTI|SWT.BORDER);
		lstTransformationSteps.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		
		for(Class<? extends ITransformationStep> t:NakedUmlEclipsePlugin.getDefault().getTransformationSteps()){
			lstTransformationSteps.add(t.getName());
		}
		String[] items = lstTransformationSteps.getItems();
		for(int i = 0;i < items.length;i++){
			if(config.getAdditionalTransformationSteps().contains(config.getClass(items[i]))){
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
		IResource[] members = null;
		try{
			members = file.getParent().members();
		}catch(CoreException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(IResource r:members){
			if(r.getLocation().removeFileExtension().lastSegment().toLowerCase().equals(txtWorkspaceIdentifier.getText().toLowerCase())){
//				this.setErrorMessage("The Project Identifier cannot be the same as the name of one of the models");
//				return;
			}
		}
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
		try{
			file.getParent().refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.okPressed();
	}
	protected void createButtonsForButtonBar(Composite parent){
		Button ok = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}
	public NakedUmlConfig getConfig(){
		return config;
	}
}
