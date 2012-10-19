package org.opaeum.uml2uim;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.utils.OpenDiagramCommand;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionService;
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
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.UimResourceUtil;
import org.opaeum.uim.util.UmlUimLinks;

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
			if(namedElement != null){
				if(namedElement instanceof EmfWorkspace){
					EmfWorkspace workspace = (EmfWorkspace) namedElement;
					runActionRecursively(namedElement, workspace);
				}else{
					OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(namedElement);
					final EmfWorkspace workspace = ouf.getEmfWorkspace();
					final Element ne = namedElement;
					ouf.getEditingDomain().getCommandStack().execute(new AbstractCommand(){
						@Override
						public boolean canExecute(){
							return true;
						}
						@Override
						public void redo(){
						}
						@Override
						public void execute(){
							runActionRecursively(ne, workspace);
						}
					});
					if(hasForm(namedElement)){
						openUimDiagram(namedElement, workspace);
					}
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
		UimModelSet ums = (UimModelSet) ws.getResourceSet();
		AbstractUserInteractionModel auim = (AbstractUserInteractionModel) UimResourceUtil
				.getUiResource(namedElement, ums, ws.getDirectoryUri()).getContents().get(0);
		new UmlUimLinks(auim.eResource(), ws);
		if(auim instanceof UserInterfaceRoot){
			openDiagrams(ums, (UserInterfaceRoot) auim);
		}else{
			for(EObject eObject:auim.eContents()){
				if(eObject instanceof UserInterfaceRoot){
					openDiagrams(ums, (UserInterfaceRoot) eObject);
				}
			}
		}
	}
	private void openDiagrams(UimModelSet ums,UserInterfaceRoot uir){
		EList<? extends Page> pages = uir.getPages();
		for(Page page:pages){
			openDiagram(ums, page);
		}
	}
	private void openDiagram(UimModelSet ums,Page auim){
		Diagram dg = ums.getInMemoryNotationResource().getDiagram(auim);
		try{
			OpenDiagramCommand c=new OpenDiagramCommand(ums.getTransactionalEditingDomain(),dg);
			ums.getTransactionalEditingDomain().getCommandStack().execute(new GMFtoEMFCommandWrapper(c));
			PapyrusMultiDiagramEditor ed;
		}catch(Exception e){
			OpaeumEclipsePlugin.logError("Could not open diagram", e);
		}
	}
	private static IEditorInput getEditorInput(Diagram diagram){
		Resource diagramResource = diagram.eResource();
		for(EObject nextEObject:diagramResource.getContents()){
			if(nextEObject == diagram){
				return new FileEditorInput(WorkspaceSynchronizer.getFile(diagramResource));
			}
			if(nextEObject instanceof Diagram){
				break;
			}
		}
		URI uri = EcoreUtil.getURI(diagram);
		String editorName = uri.lastSegment() + '#' + diagram.eResource().getContents().indexOf(diagram);
		IEditorInput editorInput = new URIEditorInput(uri, editorName);
		return editorInput;
	}
	// public static IFile getFile(URI fileUri){
	// String platformString2 = fileUri.toPlatformString(true);
	// IFile diFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString2));
	// return diFile;
	// }
	// public static URI getFileUri(Element namedElement,EmfWorkspace w){
	// try{
	// URI dirUri = w.getDirectoryUri();
	// URI fileUri = dirUri.appendSegment("ui").appendSegment(EmfWorkspace.getId(namedElement)).appendFileExtension("di");
	// return fileUri;
	// }catch(Exception ce){
	// throw new RuntimeException(ce);
	// }
	// }
}