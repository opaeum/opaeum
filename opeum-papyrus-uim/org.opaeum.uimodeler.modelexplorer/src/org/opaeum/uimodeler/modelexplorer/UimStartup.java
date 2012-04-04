package org.opaeum.uimodeler.modelexplorer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uimodeler.UimMultiDiagramEditor;

public class UimStartup implements IStartup {
	class UimWorkbenchListener implements IWorkbenchListener {
		public boolean preShutdown(IWorkbench workbench, boolean forced) {
			return true;
		}

		public void postShutdown(IWorkbench workbench) {
		}
	}

	public final class UimPageListener implements IPageListener {
		public void pageActivated(IWorkbenchPage page) {
			closeOrphanedUimModelers(page);
		}

		public void pageClosed(IWorkbenchPage page) {
			for (IEditorReference er : page.getEditorReferences()) {
				if (er.getEditor(false) instanceof UimMultiDiagramEditor) {
					UimMultiDiagramEditor e = (UimMultiDiagramEditor) er
							.getEditor(false);
					if (e.getDiagram()!=null && e.getDiagram().getElement() instanceof UserInteractionElement) {
						page.closeEditor(e, true);
					}
				}
			}
		}

		public void pageOpened(IWorkbenchPage page) {
			closeOrphanedUimModelers(page);
		}
	}

	public final class OpaeumPartListener implements IPartListener {
		public void partActivated(IWorkbenchPart part) {
			maybeSetCurrentEditContext(part);
		}

		public void partBroughtToTop(IWorkbenchPart part) {
			maybeSetCurrentEditContext(part);
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
			maybeSetCurrentEditContext(part);
		}

		public void partClosed(IWorkbenchPart part) {
		}
	}

	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				workbench.addWorkbenchListener(new UimWorkbenchListener());
				final IWorkbenchWindow window = workbench
						.getActiveWorkbenchWindow();
				if (window != null && window.getActivePage() != null) {
					window.getActivePage().addPartListener(
							new OpaeumPartListener());
					window.addPageListener(new UimPageListener());
					maybeSetCurrentEditContext(window.getActivePage()
							.getActiveEditor());
					closeOrphanedUimModelers(window.getActivePage());
				}
			}
		});
	}

	private void maybeSetCurrentEditContext(IWorkbenchPart part) {
		if (part != null) {
			IWorkbenchPartSite site = part.getSite();
			IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
			IWorkbenchPage activePage = workbenchWindow.getActivePage();
			IEditorPart editor = activePage.getActiveEditor();
			if (editor instanceof UimMultiDiagramEditor) {
				UimMultiDiagramEditor e = (UimMultiDiagramEditor) editor;
				IFile uimFile = ((IFileEditorInput) e.getEditorInput())
						.getFile();
				if (uimFile.getParent().getName().equals("ui")) {
					if (!UimContentAdapter.isListeningTo(e.getEditingDomain()
							.getResourceSet())) {
						e.getEditingDomain().getResourceSet().eAdapters()
								.add(new UimContentAdapter());
					}
					IContainer dir = uimFile.getParent().getParent();
					OpaeumEclipseContext result = OpaeumEclipseContext
							.findOrCreateContextFor(dir);
					OpaeumEclipseContext.setCurrentContext(result);
				}
			}
		}
	}

	private void closeOrphanedUimModelers(IWorkbenchPage page) {
		for (IEditorReference er : page.getEditorReferences()) {
			if (er.getEditor(false) instanceof UimMultiDiagramEditor) {
				UimMultiDiagramEditor e = (UimMultiDiagramEditor) er
						.getEditor(false);
				if (e.getDiagram() !=null &&  e.getDiagram().getElement() instanceof UserInteractionElement) {
					if (UmlUimLinks
							.getCurrentUmlLinks((UserInteractionElement) e
									.getDiagram().getElement()) == null) {
						page.closeEditor(e, true);
					}
				}
			}
		}
	}
}
