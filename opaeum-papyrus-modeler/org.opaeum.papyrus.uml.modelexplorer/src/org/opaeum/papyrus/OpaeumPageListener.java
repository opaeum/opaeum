package org.opaeum.papyrus;

import java.nio.channels.SelectableChannel;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class OpaeumPageListener implements IStartup{
	public void earlyStartup(){
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable(){
			private IFile getUmlFile(final IFileEditorInput fe){
				return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
			}

			public void run(){
				final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if(window != null && window.getActivePage() != null){
					window.getActivePage().addPartListener(new IPartListener(){
						public void partActivated(IWorkbenchPart part){
							PapyrusMultiDiagramEditor e = getPapyrusEditor(window);
							if(e != null){
								doit(e);
							}
						}
						private void doit(PapyrusMultiDiagramEditor e){
							EditingDomain ed = e.getEditingDomain();
							IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
							OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
							if(result != null){
								EObjectSelectorUI selector = new EObjectSelectorUI(){
									public void gotoEObject(EObject key){
									}
									public void pushSelection(EObject eObject){
									}
									public EObject popSelection(){
										return null;
									}
								};
								if(result.isSyncronizingWith(ed.getResourceSet(), umlFile)){
									result.setCurrentEditContext(ed, umlFile, selector);
								}else{
									result.startSynch(ed, umlFile);
								}
								result.seteObjectSelectorUI(selector);
							}
						}
						private PapyrusMultiDiagramEditor getPapyrusEditor(final IWorkbenchWindow window){
							PapyrusMultiDiagramEditor e = null;
							if(window != null && window.getActivePage() != null){
								for(IEditorReference er:window.getActivePage().getEditorReferences()){
									if(er.getEditor(false) instanceof PapyrusMultiDiagramEditor){
										e = (PapyrusMultiDiagramEditor) er.getEditor(false);
									}
								}
							}
							return e;
						}
						public void partBroughtToTop(IWorkbenchPart part){
							// TODO Auto-generated method stub
						}
						public void partClosed(IWorkbenchPart part){
							PapyrusMultiDiagramEditor e = getPapyrusEditor(window);
							if(e != null){
								if(OpaeumEclipseContext.getCurrentContext() != null){
									OpaeumEclipseContext.getCurrentContext().onClose(((IFileEditorInput) e.getEditorInput()).getFile());
								}
							}
						}
						public void partDeactivated(IWorkbenchPart part){
							// TODO Auto-generated method stub
						}
						public void partOpened(IWorkbenchPart part){
							PapyrusMultiDiagramEditor e = getPapyrusEditor(window);
							if(e != null){
								doit(e);
							}
						}
					});
				}
			}
		});
	}
}
