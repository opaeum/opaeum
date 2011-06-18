package org.nakeduml.uml2uim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.nakeduml.uim.form.UimForm;
import org.nakeduml.uim.modeleditor.UimPlugin;
import org.nakeduml.uim.modeleditor.editor.UimEditor;
import org.nakeduml.uim.util.UmlUimLinks;
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
				runActionRecursively(namedElement);
				if(hasForm(namedElement)){
					openAUIDiagram(namedElement,action);
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
		System.out.println(uri);
		uri = uri.trimFileExtension();
		System.out.println(uri);
	}
	public void openAUIDiagram(NamedElement namedElement,IAction action){
		try{
			String fileName = getFileName(namedElement,action);
			URI dirUri = namedElement.eResource().getURI().trimFileExtension().trimSegments(1);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			String platformString = dirUri.toPlatformString(true);
			IFolder folder = root.getFolder(new Path(platformString));
			UimPlugin default1 = UimPlugin.getDefault();
			IWorkbenchPage activePage = default1.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			URI fileUri = dirUri.appendSegment("forms").appendSegment(fileName).appendFileExtension("uimdi");
			if(folder.exists()){
				folder.refreshLocal(100, null);
			}
			String platformString2 = fileUri.toPlatformString(true);
			IFile diFile = root.getFile(new Path(platformString2));
			if(diFile.exists()){
				for(IEditorReference er:activePage.getEditorReferences()){
					IEditorPart curEditor = er.getEditor(false);
					if(curEditor instanceof UimEditor){
						UimEditor uimEditor = (UimEditor) curEditor;
						for(Resource r:new ArrayList<Resource>( uimEditor.getResourceSet().getResources())){
							if(r.getURI().equals(fileUri)){
								uimEditor.getSite().getPage().closeEditor(uimEditor, false);
							}
						}
					}
					
				}
				IEditorPart editorPart = IDE.openEditor(activePage, diFile, true);
				if(editorPart instanceof UimEditor){
					UimEditor uimEditor = (UimEditor) editorPart;
					UimForm referencedForm = getReferencedForm(uimEditor, namedElement);
					uimEditor.gotoEObject(referencedForm);
				}
			}
		}catch(PartInitException e){
			e.getMessage();
			e.printStackTrace();
		}catch(CoreException e){
			e.printStackTrace();
		}
	}
	public String getFileName(NamedElement namedElement, IAction action){
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
		return UmlUimLinks.getId(namedElement) + suffix;
	}
	private UimForm getReferencedForm(UimEditor editor,NamedElement objectToFind){
		String uid = UmlUimLinks.getId(objectToFind);
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
	protected void save(URI rootDir,ResourceSet r) throws IOException{
		String rootString = toString(rootDir);
		for(Resource resource:r.getResources()){
			String string = toString(resource.getURI());
			if(string!=null&&string.startsWith(rootString)){
				resource.save(new HashMap());
			}
		}
	}
	private String toString(URI rootDir){
		return rootDir.isFile() ? rootDir.toFileString() : rootDir.toPlatformString(true);
	}
}