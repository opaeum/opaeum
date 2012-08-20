package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class SelectOutputProjectAction extends AbstractOpaeumAction{
	public SelectOutputProjectAction(IStructuredSelection selection){
		super(selection, "Select Output Location");
	}
	@Override
	public void run(){
		Shell shell = Display.getCurrent().getActiveShell();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		ContainerSelectionDialog dlg= new ContainerSelectionDialog(shell, root, false, "Select output project");
		if(dlg.open()==Window.OK){
			IPath path= (IPath) dlg.getResult()[0];
			OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(((IContainer) selection.getFirstElement()));
			ctx.getConfig().setProjectNameOverride(path.lastSegment());
			reinitialiseConfig(ctx);
		}
	}
}
