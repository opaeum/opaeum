package org.opaeum.eclipse.uml.propertysections.core;

import java.io.BufferedReader;
import java.io.FileReader;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.topcased.richtext.IRichText;
import org.topcased.richtext.actions.RichTextAction;

public class RichTextUploadAction extends RichTextAction{
	private Shell shell;
	public RichTextUploadAction(Shell shell,IRichText richText){
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
		setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
		setToolTipText("Upload contents from external file"); 
		this.shell = shell;
	}
	@Override
	public void execute(IRichText richText){
		FileDialog dlg = new FileDialog(shell);
		dlg.open();
		try{
			if(dlg.getFileName() != null && dlg.getFileName().length() > 0){
				BufferedReader fr = new BufferedReader(new FileReader(dlg.getFileName()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while((line = fr.readLine()) != null){
					sb.append(line);
				}
				richText.setText(sb.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
