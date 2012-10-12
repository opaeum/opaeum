package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.internal.ide.misc.ContainerSelectionGroup;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

@SuppressWarnings("restriction")
public class SelectOutputProjectAction extends AbstractOpaeumAction{
	public SelectOutputProjectAction(IStructuredSelection selection){
		super(selection, "Select Output Location");
	}
	@Override
	public void run(){
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(((IContainer) selection.getFirstElement()));
		Shell shell = Display.getCurrent().getActiveShell();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final ContainerSelectionDialog dlg = new ContainerSelectionDialog(shell, root, false, "Select output project");
		dlg.setBlockOnOpen(false);
		dlg.open();
		Control[] children = dlg.getOkButton().getParent().getParent().getParent().getChildren();
		for(Control control:children){
			if(control instanceof Composite){
				Control[] children2 = ((Composite) control).getChildren();
				for(Control control2:children2){
					if(control2 instanceof ContainerSelectionGroup){
						ContainerSelectionGroup group = (ContainerSelectionGroup) control2;
						if(ctx.getConfig().getProjectNameOverride() != null && root.getProject(ctx.getConfig().getProjectNameOverride()).exists()){
							group.setSelectedContainer(root.getProject(ctx.getConfig().getProjectNameOverride()));
						}
					}
				}
			}
		}
		dlg.getOkButton().addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				IPath path = (IPath) dlg.getResult()[0];
				ctx.getConfig().setProjectNameOverride(path.lastSegment());
				ctx.getConfig().store();
				reinitialiseConfig(ctx);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
}
