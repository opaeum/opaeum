package org.opaeum.papyrus;

import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class OpaeumPageListener implements IStartup{
	public static final class OpaeumPartListener implements IPartListener{
		EObjectSelectorUI selector = new EObjectSelectorUI(){
			Stack<EObject> selection = new Stack<EObject>();
			public void gotoEObject(EObject key){
				for(IViewReference vr:window.getActivePage().getViewReferences()){
					IViewPart view = vr.getView(true);
					if(view instanceof ModelExplorerPageBookView){
						ModelExplorerPageBookView me = (ModelExplorerPageBookView) view;
						IViewPart viewPart = me.getActiveView();
						if(viewPart instanceof ModelExplorerView){
							CommonViewer treeViewer = ((ModelExplorerView) viewPart).getCommonViewer();
							// The common viewer is in fact a tree viewer
							Object modelElementItem = me.findElementForEObject(treeViewer, key);
							if(modelElementItem != null){
								TreePath treePath = new TreePath(new Object[]{modelElementItem});
								EObject parent = key.eContainer();
								if(parent != null){
									// workaround: in case of a pseudo parent (like "ownedConnector", the expansion
									// is not made automatically
									Object parentElement = me.findElementForEObject(treeViewer, parent);
									treeViewer.expandToLevel(parentElement, 1);
								}
								treeViewer.setSelection(new TreeSelection(treePath), true);
							}
						}
					}
				}
			}
			public void pushSelection(EObject eObject){
				selection.push(eObject);
			}
			public EObject popSelection(){
				return selection.pop();
			}
		};
		private final IWorkbenchWindow window;
		private OpaeumPartListener(IWorkbenchWindow window){
			this.window = window;
		}
		public void partActivated(IWorkbenchPart part){
		}
		private IFile getUmlFile(final IFileEditorInput fe){
			return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
		}
		public void partBroughtToTop(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		private void maybeSetCurrentEditContext(IWorkbenchPart part){
			if(part instanceof PapyrusMultiDiagramEditor){
				PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) part;
				IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
				OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
				result.setCurrentEditContext(e.getEditingDomain(), umlFile, selector);
			}
		}
		public void partClosed(IWorkbenchPart part){
			if(part instanceof PapyrusMultiDiagramEditor){
				PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) part;
				IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
				OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
				if(result != null){
					result.onClose(((IFileEditorInput) e.getEditorInput()).getFile());
				}
			}
		}
		public void partDeactivated(IWorkbenchPart part){
		}
		public void partOpened(IWorkbenchPart part){
			if(part instanceof PapyrusMultiDiagramEditor){
				PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) part;
				EditingDomain ed = e.getEditingDomain();
				IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
				OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
				if(result != null){
					result.startSynch(ed, umlFile);
					result.seteObjectSelectorUI(selector);
				}
			}
		}
	}
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
					window.getActivePage().addPartListener(new OpaeumPartListener(window));
				}
			}
		});
	}
}
