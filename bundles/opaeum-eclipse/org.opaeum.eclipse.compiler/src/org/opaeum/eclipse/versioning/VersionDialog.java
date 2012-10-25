package org.opaeum.eclipse.versioning;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.eclipse.VersionText;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.feature.OpaeumConfig;

public class VersionDialog extends TitleAreaDialog{
	private VersionText txtOldVersionNumber;
	private OpaeumConfig config;
	private VersionText txtNewVersionNumber;
	private IFolder modelDirectory;
	public VersionDialog(Shell parentShell,IFolder dir){
		super(parentShell);
		this.modelDirectory = dir;
		config = OpaeumEclipseContext.findOrCreateContextFor(modelDirectory).getConfig();
	}
	protected Control createContents(Composite parent){
		Control contents = super.createContents(parent);
		setTitle("Opaeum Versioning Tool");
		setMessage("Please provide input", IMessageProvider.NONE);
		return contents;
	}
	protected Control createDialogArea(Composite parent){
		Composite composite = (Composite) super.createDialogArea(parent);
		Composite panel = new Composite(composite, 0);
		panel.setLayout(new GridLayout(2, true));
		new Label(panel, 0).setText("Current Version Number");
		txtOldVersionNumber = new VersionText(panel, SWT.SINGLE | SWT.BORDER);
		txtOldVersionNumber.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtOldVersionNumber.setVersion(config.getVersion());
		txtOldVersionNumber.setEditable(false);
		new Label(panel, 0).setText("New Version Number");
		txtNewVersionNumber = new VersionText(panel, SWT.SINGLE | SWT.BORDER);
		txtNewVersionNumber.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtNewVersionNumber.setVersion(config.getVersion());
		return composite;
	}
	@Override
	protected void okPressed(){
		try{
			if(!txtNewVersionNumber.isValid()){
				setMessage(txtNewVersionNumber.getText() + " is in an incorrect format. Should be #.#.#");
				txtNewVersionNumber.setFocus();
			}else{
				IFolder newFolder = modelDirectory.getFolder(txtNewVersionNumber.getText());
				if(!newFolder.exists()){
					newFolder.create(true, true, null);
				}
				for(IResource m:modelDirectory.members()){
					if(!m.getFullPath().equals(newFolder.getFullPath()) || m.getName().startsWith(".")){
						if(m instanceof IFolder){
							IFolder folder = newFolder.getFolder(m.getName());
							m.copy(folder.getFullPath(), true, null);
						}else if(m instanceof IFile){
							IFile file = newFolder.getFile(m.getName());
							m.copy(file.getFullPath(), true, null);
						}
					}
				}
				config.setVersion(txtNewVersionNumber.getVersion());
				super.okPressed();
			}
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
