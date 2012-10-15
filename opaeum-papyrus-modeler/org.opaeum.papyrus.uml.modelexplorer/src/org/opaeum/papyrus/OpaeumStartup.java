package org.opaeum.papyrus;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.navigator.CommonViewer;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.feature.OpaeumConfig;

import com.google.common.collect.Lists;

@SuppressWarnings("restriction")
public class OpaeumStartup implements IStartup{
	private final class PapyrusEObjectSelectorUI implements EObjectSelectorUI{
		private IWorkbenchWindow window;
		Stack<EObject> selection = new Stack<EObject>();
		public PapyrusEObjectSelectorUI(IWorkbenchWindow workbenchWindow){
			this.window = workbenchWindow;
		}
		public void gotoEObject(EObject key){
			for(IViewReference vr:window.getActivePage().getViewReferences()){
				IViewPart view = vr.getView(true);
				if(view instanceof ModelExplorerPageBookView){
					ModelExplorerPageBookView me = (ModelExplorerPageBookView) view;
					IViewPart viewPart = me.getActiveView();
					if(viewPart instanceof ModelExplorerView){
						ModelExplorerView modelExplorerView = (ModelExplorerView) viewPart;
						CommonViewer treeViewer = modelExplorerView.getCommonViewer();
						// The common viewer is in fact a tree viewer
						ModelExplorerView.reveal(Lists.newArrayList(key), treeViewer);
						// Object modelElementItem = me.findElementForEObject(treeViewer, key);
						// if(modelElementItem != null){
						// TreePath treePath = new TreePath(new Object[]{modelElementItem});
						// EObject parent = key.eContainer();
						// if(parent != null){
						// // workaround: in case of a pseudo parent (like "ownedConnector", the expansion
						// // is not made automatically
						// Object parentElement = me.findElementForEObject(treeViewer, parent);
						// treeViewer.expandToLevel(parentElement, 1);
						// }
						// modelExplorerView.revealSemanticElement(Arrays.asList(key));
						// treeViewer.setSelection(new StructuredSelection(modelElementItem), true);
						// // modelExplorerView.selectReveal(new TreeSelection(treePath));
						// }
					}
				}
			}
		}
		public void pushSelection(EObject eObject){
			if(selection.isEmpty() ||  selection.peek() != eObject){
				selection.push(eObject);
			}
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
				PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor) editor;
				associateOpaeumContext(papyrusEditor);
			}
		}
		public void partClosed(IWorkbenchPart part){
			if(part instanceof PapyrusMultiDiagramEditor){
				PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) part;
				IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
				if(!(umlFile.getParent().getName().equals("ui") || umlFile.getParent().getName().equals("simulation"))){
					OpaeumEclipseContext result = OpaeumEclipseContext.getContextFor(umlFile.getParent());
					if(result != null){
						result.onClose(umlFile);
					}
				}
			}
		}
		public void partDeactivated(IWorkbenchPart part){
		}
		public void partOpened(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
	}
	private ISaveParticipant participant = new ISaveParticipant(){
		public void saving(ISaveContext context) throws CoreException{
		}
		public void rollback(ISaveContext context){
		}
		public void prepareToSave(ISaveContext context) throws CoreException{
		}
		public void doneSaving(ISaveContext context){
			for(IPath f:context.getFiles()){
				if(f.lastSegment().endsWith(".uml")){
					IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(f);
					OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(file);
					if(ouf != null){
						ouf.onSave(new NullProgressMonitor());
					}
				}
			}
		}
	};
	public void earlyStartup(){
		try{
			ResourcesPlugin.getWorkspace().addSaveParticipant(OpaeumEclipsePlugin.PLUGIN_ID, participant);
		}catch(CoreException e){
			OpaeumEclipsePlugin.logError("Opaeum SaveParticipant not registered", e);
		}
		OpaeumConfig.registerClass(PapyrusErrorMarker.class);
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable(){
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
		if(!(umlFile.getParent().getName().equals("ui") || umlFile.getParent().getName().equals("simulation"))){
			final OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlFile.getParent());
			((PapyrusErrorMarker) result.getErrorMarker()).setServiceRegistry(e.getServicesRegistry());
			if(result.getEditingContextFor(umlFile) == null){
				EcoreUtil.resolveAll(e.getEditingDomain().getResourceSet());
				((PapyrusErrorMarker) result.getErrorMarker()).setServiceRegistry(e.getServicesRegistry());
				final IWorkbenchWindow workbenchWindow = e.getSite().getWorkbenchWindow();
				result.startSynch(e.getEditingDomain(), umlFile, new PapyrusEObjectSelectorUI(workbenchWindow));
				try{
					workbenchWindow.getActivePage().showView("org.eclipse.papyrus.views.modelexplorer.modelexplorer");
				}catch(PartInitException e1){
					// nice to have
				}
			}
		}
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchActivitySupport activitySupport = PlatformUI.getWorkbench().getActivitySupport();
		Set<String> enabledActivities = new HashSet<String>();
		String id = "org.opaeum.papyrus.uimadapter.activity";
		IActivity activity = activitySupport.getActivityManager().getActivity(id);
		if(activity.isDefined()){
			activitySupport.setEnabledActivityIds(enabledActivities);
		}
		if(window instanceof WorkbenchWindow){
			ICoolBarManager coolBarManager = null;
			if(((WorkbenchWindow) window).getCoolBarVisible()){
				coolBarManager = ((WorkbenchWindow) window).getCoolBarManager2();
			}
			IContributionItem[] items = coolBarManager.getItems();
			boolean changed = false;
			for(IContributionItem item:items){
				if(item.getId().toLowerCase().contains("papyrus")){
					try{
						changed = true;
						System.out.println("ToolItem removed:" + item.getId());
						item.dispose();
					}catch(Exception e2){
						// nice to have
					}
				}
			}
			if(changed){
				// coolBarManager.update(true);
			}
		}
	}
}
