package org.opaeum.eclipse.menu;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public abstract class AbstractReverseEngineerAction extends AbstractOpaeumAction{
	protected static Command DO_NOTHING = new AbstractCommand(){
		@Override
		public void redo(){
		}
		@Override
		public void execute(){
		}
	};
	public AbstractReverseEngineerAction(IStructuredSelection selection,String name){
		super(selection, name);
	}
	public void run(){
		// TODO support uml files to be opened
		Shell shell = Display.getCurrent().getActiveShell();
		Collection<IFile> files = new HashSet<IFile>();
		for(IWorkbenchWindow w:PlatformUI.getWorkbench().getWorkbenchWindows()){
			for(IWorkbenchPage p:w.getPages()){
				for(IEditorReference e:p.getEditorReferences()){
					try{
						if(e.getEditorInput() instanceof IFileEditorInput){
							IFileEditorInput editorInput = (IFileEditorInput) e.getEditorInput();
							if(editorInput.getFile().getParent().findMember("opaeum.properties") != null){
								OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(editorInput.getFile().getParent());
								for(IResource r:editorInput.getFile().getParent().members()){
									if(r.getName().endsWith(".uml")){
										if(ctx.getOpenUmlFileFor((IFile) r) != null){
											files.add((IFile) r);
										}
									}
								}
							}
						}
					}catch(PartInitException e1){
						e1.printStackTrace();
					}catch(CoreException e2){
						e2.printStackTrace();
					}
				}
			}
		}
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, new LabelProvider(){
			@Override
			public String getText(Object element){
				IFile file = (IFile) element;
				return file.getProject().getName() + "/" + file.getProjectRelativePath();
			}
		});
		dialog.setElements(files.toArray());
		dialog.setTitle("Models in Workspace");
		dialog.setMessage("Select the targetmodel:");
		dialog.open();
		if(dialog.getFirstResult() != null){
			IFile file = (IFile) dialog.getFirstResult();
			OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor(file.getParent());
			if(ctx != null){
				final OpenUmlFile ouf = ctx.getOpenUmlFileFor(file);
				if(ouf != null){
					ouf.suspend();
					ouf.getEditingDomain().getCommandStack().execute(buildCommand(ouf.getModel()));
					ouf.resumeAndCatchUp();
				}
			}
			try{
				file.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			}catch(CoreException e){
				e.printStackTrace();
			}
		}
	}
	protected abstract Command buildCommand(org.eclipse.uml2.uml.Package model);
}
