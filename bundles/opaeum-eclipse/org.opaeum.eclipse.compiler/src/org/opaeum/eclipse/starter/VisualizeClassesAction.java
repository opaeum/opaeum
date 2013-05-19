package org.opaeum.eclipse.starter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.eclipse.newchild.IDiagramCreator;
import org.opaeum.eclipse.newchild.RelationshipDirection;

public class VisualizeClassesAction extends AbstractOpaeumAction{
	Set<Element> elements=new HashSet<Element>();
	public VisualizeClassesAction(IStructuredSelection selection){
		super(selection, "Visualize Associations");
		Object[] array = selection.toArray();
		for(Object object:array){
			EObject e = EmfElementFinder.adaptObject(object);
			if(e instanceof Class){
				elements.add((Element) e);
			}
		}
	}
	@Override
	public void run(){
		Set<IDiagramCreator> diagramCreators = OpaeumEclipsePlugin.getDefault().getDiagramCreators();
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		for(IDiagramCreator c:diagramCreators){
			if(c.matches(activeEditor)){
				c.createDiagram( "Associations", elements, activeEditor, RelationshipDirection.BOTH, UMLPackage.eINSTANCE.getAssociation());
			}
		}
	}
}
