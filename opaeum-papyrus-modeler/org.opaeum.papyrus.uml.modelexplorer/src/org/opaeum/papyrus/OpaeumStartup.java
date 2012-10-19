package org.opaeum.papyrus;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.opaeum.eclipse.OpaeumErrorMarker;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.feature.OpaeumConfig;

public class OpaeumStartup implements IStartup{
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
	private IResourceChangeListener participant = new IResourceChangeListener(){
		public void resourceChanged(IResourceChangeEvent event){
			if(event.getType() == IResourceChangeEvent.POST_CHANGE){
				Set<IFile> files = new HashSet<IFile>();
				addUmlFiles(files, event.getDelta());
				for(IFile f:files){
					OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(f);
					if(ouf != null){
						ouf.onSave(new NullProgressMonitor());
					}
				}
			}
		}
	};
	private void addUmlFiles(Set<IFile> files,IResourceDelta d){
		if(d.getResource() instanceof IFile){
			if(d.getResource().getFileExtension().equals("uml")){
				files.add((IFile) d.getResource());
			}
		}
		for(IResourceDelta c:d.getAffectedChildren()){
			addUmlFiles(files, c);
		}
	}
	public void earlyStartup(){
		ResourcesPlugin.getWorkspace().addResourceChangeListener(participant);
		OpaeumConfig.registerClass(OpaeumErrorMarker.class);
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
			if(result.getOpenUmlFileFor(umlFile) == null){
				final IWorkbenchWindow workbenchWindow = e.getSite().getWorkbenchWindow();
				Display.getDefault().timerExec(2000, new Runnable(){
					public void run(){
						try{
							// TODO Auto-generated method stub
//							workbenchWindow.getActivePage().showView("org.opaeum.papyrus.uml.modelexplorer");
//							IViewPart ex = workbenchWindow.getActivePage().findView("org.eclipse.papyrus.views.modelexplorer.modelexplorer");
//							if(ex != null){
//								workbenchWindow.getActivePage().hideView(ex);
//							}
						}catch(Exception e1){
							// nice to have
						}
					}
				});
			}
		}
		IWorkbenchActivitySupport activitySupport = PlatformUI.getWorkbench().getActivitySupport();
		Set<String> enabledActivities = new HashSet<String>();
		String id = "org.opaeum.papyrus.uimadapter.activity";
		IActivity activity = activitySupport.getActivityManager().getActivity(id);
		if(activity.isDefined()){
			activitySupport.setEnabledActivityIds(enabledActivities);
		}
		// IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// if(window instanceof WorkbenchWindow){
		// ICoolBarManager coolBarManager = null;
		// if(((WorkbenchWindow) window).getCoolBarVisible()){
		// coolBarManager = ((WorkbenchWindow) window).getCoolBarManager2();
		// }
		// IContributionItem[] items = coolBarManager.getItems();
		// boolean changed = false;
		// for(IContributionItem item:items){
		// if(item.getId().toLowerCase().contains("papyrus")){
		// try{
		// changed = true;
		// item.dispose();
		// }catch(Exception e2){
		// // nice to have
		// }
		// }
		// }
		// if(changed){
		// coolBarManager.update(true);
		// }
		// }
	}
}
