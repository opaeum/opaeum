package org.opaeum.uimodeler.modelexplorer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class UimPageListener implements IStartup{
	public final class OpaeumPartListener implements IPartListener{
		public void partActivated(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		public void partBroughtToTop(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		public void partDeactivated(IWorkbenchPart part){
		}
		public void partOpened(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		public void partClosed(IWorkbenchPart part){
			
		}
	}
	public void earlyStartup(){
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable(){
			public void run(){
				final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if(window != null && window.getActivePage() != null){
					window.getActivePage().addPartListener(new OpaeumPartListener());
					maybeSetCurrentEditContext(window.getActivePage().getActiveEditor());
				}
			}
		});
	}
	private void maybeSetCurrentEditContext(IWorkbenchPart part){
		IEditorPart editor = part.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
		if(editor instanceof PapyrusMultiDiagramEditor){
			PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) editor;
			IFile uimFile = ((IFileEditorInput) e.getEditorInput()).getFile();
			IContainer dir=uimFile.getParent().getParent();
			if(uimFile.getParent().findMember("opaeum.properties")!=null){
				dir=uimFile.getParent();
			}
			OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(dir);
			OpaeumEclipseContext.setCurrentContext(result);
		}
	}
}
