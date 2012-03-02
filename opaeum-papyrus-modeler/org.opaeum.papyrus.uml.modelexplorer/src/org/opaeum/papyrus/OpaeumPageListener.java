package org.opaeum.papyrus;

import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.lifecycleevents.ISaveAndDirtyService;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.feature.OpaeumConfig;

public class OpaeumPageListener implements IStartup{
	private final class PapyrusEObjectSelectorUI implements EObjectSelectorUI{
		private IWorkbenchWindow window;
		Stack<EObject> selection = new Stack<EObject>();
		public PapyrusEObjectSelectorUI(IWorkbenchWindow workbenchWindow){
			this.window=workbenchWindow;
		}
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
	}
	public final class OpaeumPartListener implements IPartListener{
		public void partActivated(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		public void partBroughtToTop(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		private void maybeSetCurrentEditContext(IWorkbenchPart part){
			IEditorPart editor = part.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
			if(editor instanceof PapyrusMultiDiagramEditor){
				associateOpaeumContext((PapyrusMultiDiagramEditor) editor);
			}
		}
		public void partClosed(IWorkbenchPart part){
			if(part instanceof PapyrusMultiDiagramEditor){
				PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) part;
				IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
				OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
				if(result != null){
					result.onClose(umlFile);
				}
			}
		}
		public void partDeactivated(IWorkbenchPart part){
		}
		public void partOpened(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
	}
	public void earlyStartup(){
		OpaeumConfig.registerClass(PapyrusErrorMarker.class);
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable(){
			private IFile getUmlFile(final IFileEditorInput fe){
				return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
			}
			public void run(){
				final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if(window != null && window.getActivePage() != null){
					window.getActivePage().addPartListener(new OpaeumPartListener());
					if(window.getActivePage().getActiveEditor() instanceof PapyrusMultiDiagramEditor){
						associateOpaeumContext((PapyrusMultiDiagramEditor) window.getActivePage().getActiveEditor());
					}
				}
			}
		});
	}
	private IFile getUmlFile(final IFileEditorInput fe){
		return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
	}
	private void associateOpaeumContext(PapyrusMultiDiagramEditor e){
		final IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
		final OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
		((PapyrusErrorMarker) result.getErrorMarker()).setServiceRegistry(e.getServicesRegistry());
		if(result.getEditingContextFor(umlFile) == null){
			((PapyrusErrorMarker) result.getErrorMarker()).setServiceRegistry(e.getServicesRegistry());
			result.startSynch(e.getEditingDomain(), umlFile);
			result.seteObjectSelectorUI(new PapyrusEObjectSelectorUI(e.getSite().getWorkbenchWindow()));
			ISaveAndDirtyService saveAndDirtyService = getSaveAndDirtyService(e);
			saveAndDirtyService.registerIsaveablePart(new ISaveablePart(){
				public boolean isSaveOnCloseNeeded(){
					return true;
				}
				public boolean isSaveAsAllowed(){
					return false;
				}
				public boolean isDirty(){
					return true;
				}
				public void doSaveAs(){
				}
				public void doSave(IProgressMonitor monitor){
					result.onSave(monitor, umlFile);
				}
			});
			result.setCurrentEditContext(e.getEditingDomain(), umlFile, result.geteObjectSelectorUI());
		}
	}
	private ISaveAndDirtyService getSaveAndDirtyService(PapyrusMultiDiagramEditor e){
		ISaveAndDirtyService saveAndDirtyService;
		try{
			saveAndDirtyService = e.getServicesRegistry().getService(ISaveAndDirtyService.class);
		}catch(ServiceException e1){
			throw new RuntimeException(e1);
		}
		return saveAndDirtyService;
	}
}
