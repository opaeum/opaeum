package org.opeum.uml2uim;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.uim.form.UimForm;
import org.opeum.uim.modeleditor.UimPlugin;
import org.opeum.uim.modeleditor.editor.UimEditor;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public abstract class AbstractUimGenerationAction{
	private IStructuredSelection selection;
	public void setActivePart(IAction action,IWorkbenchPart targetPart){
	}
	protected boolean hasForm(NamedElement n){
		return n instanceof org.eclipse.uml2.uml.Class || n instanceof State || n instanceof StateMachine || n instanceof Activity || n instanceof OpaqueAction
				|| n instanceof Operation;
	}
	public void selectionChanged(IAction action,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	public void run(IAction action){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			NamedElement namedElement = null;
			if(element instanceof NamedElement){
				namedElement = (NamedElement) element;
			}else if(element instanceof EMFGraphNodeEditPart){
				EMFGraphNodeEditPart editPart = (EMFGraphNodeEditPart) element;
				if(editPart.getEObject() instanceof NamedElement){
					namedElement = (NamedElement) editPart.getEObject();
				}
			}
			if(namedElement != null){
				runActionRecursively(namedElement, action);
				if(hasForm(namedElement)){
					openUimDiagram(namedElement, action);
				}
			}
		}
	}
	protected abstract void runActionRecursively(NamedElement eObject,IAction action);
	public static void main(String[] args) throws Exception{
		URI uri = URI.createFileURI(new File(".").getCanonicalPath());
		uri = uri.appendSegment("test");
		uri = uri.appendFileExtension("uim");
		uri = uri.trimSegments(0);
		uri = uri.trimFileExtension();
	}
	public void openUimDiagram(NamedElement namedElement,IAction action){
		String fileName = getFileName(namedElement, action);
		openEditor(namedElement, fileName);
	}
	public static void openEditor(NamedElement namedElement,String fileName){
		try{
			URI fileUri = getFileUri(namedElement, fileName);
			refreshContainingFolder(fileUri);
			IFile diFile = getFile(fileUri);
			if(diFile.exists()){
				for(IEditorReference er:UimPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()){
					IEditorPart curEditor = er.getEditor(false);
					if(curEditor instanceof UimEditor){
						UimEditor uimEditor = (UimEditor) curEditor;
						for(Resource r:new ArrayList<Resource>(uimEditor.getResourceSet().getResources())){
							if(r.getURI().equals(fileUri)){
								uimEditor.getSite().getPage().closeEditor(uimEditor, false);
							}
						}
					}
				}
				IEditorPart editorPart = IDE.openEditor(UimPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
				if(editorPart instanceof UimEditor){
					UimEditor uimEditor = (UimEditor) editorPart;
					UimForm referencedForm = getReferencedForm(uimEditor, namedElement);
					uimEditor.gotoEObject(referencedForm);
				}
			}
		}catch(PartInitException e){
			OpeumEclipsePlugin.getDefault().getLog().log(new Status(Status.ERROR, OpeumEclipsePlugin.getPluginId(), e.getMessage(),e));
			e.printStackTrace();
		}catch(CoreException e){
			OpeumEclipsePlugin.getDefault().getLog().log(new Status(Status.ERROR, OpeumEclipsePlugin.getPluginId(), e.getMessage(),e));
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
			URI fileUri = dirUri.appendSegment("forms").appendSegment(fileName).appendFileExtension("uimdi");
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
	public String getFileName(NamedElement namedElement,IAction action){
		String suffix = "";
		if(namedElement instanceof StateMachine || namedElement instanceof Activity){
			suffix = "Editor";
		}else if(namedElement instanceof org.eclipse.uml2.uml.Class){
			if(true){
				// Check action id;
				suffix = "Editor";
			}else{
				suffix = "Creator";
			}
		}else if(namedElement instanceof Operation){
			if(true){
				// Check action id;
				suffix = "Invoker";
			}else{
				suffix = "Task";
			}
		}
		return OpeumEclipseContext.getCurrentContext().getId(namedElement) + suffix;
	}
	private static UimForm getReferencedForm(UimEditor editor,NamedElement objectToFind){
		String uid = OpeumEclipseContext.getCurrentContext().getId(objectToFind);
		ResourceSet set = editor.getEditingDomain().getResourceSet();
		for(Resource r:set.getResources()){
			EList<EObject> contents = r.getContents();
			for(EObject eObject:contents){
				if(eObject instanceof UimForm && ((UimForm) eObject).getPanel().getUmlElementUid().equals(uid)){
					return (UimForm) eObject;
				}
			}
		}
		return null;
	}
//	protected static OpeumEclipseContext findOpeumEditor(NamedElement modelElement){
//		return OpeumEclipseContext.findOpeumEditor(modelElement);
//	}
}