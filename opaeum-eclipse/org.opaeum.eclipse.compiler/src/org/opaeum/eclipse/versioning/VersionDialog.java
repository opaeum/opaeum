package org.opaeum.eclipse.versioning;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.feature.OpaeumConfig;

public class VersionDialog extends TitleAreaDialog{
	private Text txtOldVersionNumber;
	private OpaeumConfig config;
	private Text txtNewVersionNumber;
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
		txtOldVersionNumber = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtOldVersionNumber.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtOldVersionNumber.setText(config.getMavenGroupVersion());
		txtOldVersionNumber.setEditable(false);
		new Label(panel, 0).setText("New Version Number");
		txtNewVersionNumber = new Text(panel, SWT.SINGLE | SWT.BORDER);
		txtNewVersionNumber.setLayoutData(new GridData(SWT.FILL, GridData.BEGINNING, true, false));
		txtNewVersionNumber.setText(config.getMavenGroupVersion());
		txtNewVersionNumber.addKeyListener(new KeyListener(){
			@Override
			public void keyReleased(KeyEvent e){
			}
			@Override
			public void keyPressed(KeyEvent e){
				if(!Character.isISOControl(e.character)){
					System.out.println((int) e.character);
					if(e.character == '.'){
						if(txtNewVersionNumber.getCaretPosition() == 0 || txtNewVersionNumber.getText().charAt(txtNewVersionNumber.getCaretPosition() - 1) == '.'
								|| txtNewVersionNumber.getText().split("\\.").length > 2){
							e.doit = false;
						}
					}else if(e.character < '0' || e.character > '9'){
						e.doit = false;
					}
				}
			}
		});
		return composite;
	}
	@Override
	protected void okPressed(){
		try{
			if(!OpaeumConfig.isValidVersionNumber(txtNewVersionNumber.getText())){
				setMessage(txtNewVersionNumber.getText() + " is in an incorrect format. Should be #.#.#");
				txtNewVersionNumber.setFocus();
			}else{
				String oldVersion = config.getMavenGroupVersion().replace("-SNAPSHOT", "");
				config.setMavenGroupVersion(oldVersion);
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
				config.setMavenGroupVersion(txtNewVersionNumber.getText());
				super.okPressed();
			}
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
