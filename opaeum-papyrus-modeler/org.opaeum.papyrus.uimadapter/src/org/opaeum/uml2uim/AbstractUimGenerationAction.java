package org.opaeum.uml2uim;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uimodeler.UimMultiDiagramEditor;

public abstract class AbstractUimGenerationAction extends Action{
	public AbstractUimGenerationAction(String text){
		super(text);
	}
	protected boolean hasForm(Element n){
		return n instanceof org.eclipse.uml2.uml.Class || n instanceof State || n instanceof StateMachine || n instanceof Activity
				|| n instanceof OpaqueAction || n instanceof Operation;
	}
	@Override
	public void run(){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		IStructuredSelection selection = (IStructuredSelection) s.getSelection();
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			Element namedElement = null;
			if(element instanceof NamedElement){
				namedElement = (NamedElement) element;
			}else if(element instanceof IContainer){
				OpaeumEclipseContext oec = OpaeumEclipseContext.findOrCreateContextFor((IContainer) element);
				namedElement = oec.loadDirectory(new NullProgressMonitor());
			}else if(element instanceof IAdaptable){
				Object a = ((IAdaptable) element).getAdapter(EObject.class);
				if(a instanceof NamedElement){
					namedElement = (NamedElement) a;
				}
			}
			EmfWorkspace workspace = null;
			if(namedElement != null){
				if(namedElement instanceof EmfWorkspace){
					workspace = (EmfWorkspace) namedElement;
				}else{
					OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor(namedElement);
					if(ctx == null){
						ctx = OpaeumEclipseContext.getCurrentContext();
					}
					workspace = ctx.getCurrentEmfWorkspace();
				}
				runActionRecursively(namedElement, workspace);
				if(hasForm(namedElement)){
					openUimDiagram(namedElement, workspace);
				}
			}
		}
	}
	protected abstract void runActionRecursively(Element eObject,EmfWorkspace workspace);
	public static void main(String[] args) throws Exception{
		URI uri = URI.createFileURI(new File(".").getCanonicalPath());
		uri = uri.appendSegment("test");
		uri = uri.appendFileExtension("uim");
		uri = uri.trimSegments(0);
		uri = uri.trimFileExtension();
	}
	public void openUimDiagram(Element namedElement,EmfWorkspace ws){
		openEditor(namedElement, ws);
	}
	public static void openEditor(final Element namedElement,EmfWorkspace ws){
		try{
			URI fileUri = getFileUri(namedElement, ws);
			refreshContainingFolder(fileUri);
			IFile diFile = getFile(fileUri);
			if(diFile.exists()){
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				UimMultiDiagramEditor editorPart = (UimMultiDiagramEditor) page.openEditor(new FileEditorInput(diFile),
						"org.opaeum.uimodeler.OpaeumMultiDiagramEditor", true);
				// TODO navigate to selected object
				// TODO associate currentEmfWorkspace with this instance of the editor
				// TODO close this editor if the associated papyrus editor is closed
			}
		}catch(PartInitException e){
			OpaeumEclipsePlugin.getDefault().getLog().log(new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), e.getMessage(), e));
			e.printStackTrace();
		}catch(CoreException e){
			OpaeumEclipsePlugin.getDefault().getLog().log(new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), e.getMessage(), e));
			e.printStackTrace();
		}
	}
	public static IFile getFile(URI fileUri){
		String platformString2 = fileUri.toPlatformString(true);
		IFile diFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString2));
		return diFile;
	}
	public static URI getFileUri(Element namedElement,EmfWorkspace w){
		try{
			URI dirUri = w.getDirectoryUri();
			URI fileUri = dirUri.appendSegment("ui").appendSegment(EmfWorkspace.getId(namedElement)).appendFileExtension("di");
			return fileUri;
		}catch(Exception ce){
			throw new RuntimeException(ce);
		}
	}
	private static void refreshContainingFolder(URI dirUri) throws CoreException{
		IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(dirUri.trimSegments(1).toPlatformString(true)));
		if(folder.exists()){
			folder.refreshLocal(100, null);
		}
	}
}