package org.nakeduml.uml2uim;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;


import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public abstract class AbstractUIMGenerationAction {

	private IStructuredSelection selection;
	private static final String SOURCE = "org.nakeduml.uml2uim";

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// do nothing
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
	}

	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		for (Iterator it = selection.iterator(); it.hasNext();) {
			Object element = it.next();
			if (element instanceof NamedElement) {
				runActionRecursively((NamedElement) element);
			} else if (element instanceof EMFGraphNodeEditPart) {
				EMFGraphNodeEditPart editPart = (EMFGraphNodeEditPart) element;
				if (editPart.getEObject() instanceof NamedElement) {
					runActionRecursively((NamedElement) editPart.getEObject());
				}
			}
		}
	}

	protected abstract void runActionRecursively(NamedElement eObject);
	public static void main(String[] args){
		URI uri = URI.createFileURI(new File("test.file").getAbsolutePath());
		uri=uri.trimSegments(3);
		System.out.println(uri);
		uri=uri.trimFileExtension();
		System.out.println(uri);
	}
	protected UserInteractionModel getUimModel(Model umlModel)
			throws IOException {
		URI uri = umlModel.eResource().getURI();
		URI uimUri = uri.trimFileExtension();
		uimUri = uimUri.appendFileExtension("uim");
		ResourceSet rs = umlModel.eResource().getResourceSet();
		
		UserInteractionModel uimModel = (UserInteractionModel) getRootObject(
				uimUri, rs);
		if (uimModel == null) {
			Resource newResource = rs.createResource(uimUri);
			uimModel = UIMFactory.eINSTANCE.createUserInteractionModel();
			uimModel.setName(umlModel.getName());
			newResource.getContents().add(uimModel);
			uimModel.setUmlModel(umlModel);
			newResource.save(null);
			umlModel.eResource().save(null);
		}
		return uimModel;
	}

	protected Diagrams getRootDiagrams(UserInteractionModel uimModel)
			throws IOException {
		Diagrams diagrams;
		URI diagmsUri = uimModel.eResource().getURI().trimFileExtension()
				.appendFileExtension("uimdi");
		diagrams = (Diagrams) getRootObject(diagmsUri, uimModel.eResource()
				.getResourceSet());
		if (diagrams == null) {
			diagrams = DiagramsFactory.eINSTANCE.createDiagrams();
			diagrams.setModel(uimModel);
			Diagram diag = DiagramInterchangeFactory.eINSTANCE.createDiagram();
			EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE
					.createEMFSemanticModelBridge();
			bridge.setPresentation("org.nakeduml.uim.folderdiagram");
			bridge.setElement(uimModel);
			diag.setSemanticModel(bridge);
			diag.setName(uimModel.getName());
			diag.setPosition(new Point());
			diag.setSize(new Dimension(100, 100));
			diag.setViewport(new Point());
			diagrams.getDiagrams().add(diag);
			Resource diagramResource = uimModel.eResource().getResourceSet()
					.createResource(diagmsUri);
			diagramResource.getContents().add(diagrams);
			diagramResource.save(null);
		}
		return diagrams;
	}

	private EObject getRootObject(URI uimUri, ResourceSet resourceSet) {
		try {
//TODO create new resource set for the UIM Model
			
			Resource resource = resourceSet.getResource(uimUri, true);
			if (resource == null) {
				return null;
			} else {
				resource.unload();
				resource.load(new HashMap());//force reload
				if (resource.getContents().isEmpty()) {
					return null;
				} else {
					return resource.getContents().get(0);
				}
			}
		} catch (Exception re) {
			re.printStackTrace(System.out);
			System.out.println("SynchronizeAction.getRootObject()");
			return null;
		}
	}

}