package org.opaeum.uml2uim;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class AbstractUimGenerationAction extends Action{
	public AbstractUimGenerationAction(String text){
		super(text);
	}
	protected boolean hasForm(NamedElement n){
		return n instanceof org.eclipse.uml2.uml.Class || n instanceof State || n instanceof StateMachine || n instanceof Activity
				|| n instanceof OpaqueAction || n instanceof Operation;
	}
	@Override
	public void run(){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		IStructuredSelection selection = (IStructuredSelection) s.getSelection();
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			NamedElement namedElement = null;
			if(element instanceof NamedElement){
				namedElement = (NamedElement) element;
			}else if(element instanceof IAdaptable){
				Object a = ((IAdaptable) element).getAdapter(EObject.class);
				if(a instanceof NamedElement){
					namedElement = (NamedElement) a;
				}
			}
			if(namedElement != null){
				runActionRecursively(namedElement);
				if(hasForm(namedElement)){
					openUimDiagram(namedElement);
				}
			}
		}
	}
	protected abstract void runActionRecursively(NamedElement eObject);
	public static void main(String[] args) throws Exception{
		URI uri = URI.createFileURI(new File(".").getCanonicalPath());
		uri = uri.appendSegment("test");
		uri = uri.appendFileExtension("uim");
		uri = uri.trimSegments(0);
		uri = uri.trimFileExtension();
	}
	public void openUimDiagram(NamedElement namedElement){
		String fileName = getFileName(namedElement);
		openEditor(namedElement, fileName);
	}
	public static void openEditor(NamedElement namedElement,String fileName){
		try{
			URI fileUri = getFileUri(namedElement, fileName);
			refreshContainingFolder(fileUri);
			IFile diFile = getFile(fileUri);
			if(diFile.exists()){
				PapyrusMultiDiagramEditor editorPart = (PapyrusMultiDiagramEditor) IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
				Resource r = editorPart. getDiagram().getElement().eResource();
				new UmlUimLinks(r, OpaeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace());
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
	public static URI getFileUri(NamedElement namedElement,String fileName){
		try{
			URI dirUri = namedElement.eResource().getURI().trimFileExtension().trimSegments(1);
			URI fileUri = dirUri.appendSegment("ui").appendSegment(fileName).appendFileExtension("di");
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
	public String getFileName(NamedElement namedElement){
		return OpaeumEclipseContext.getCurrentContext().getId(namedElement);
	}
}