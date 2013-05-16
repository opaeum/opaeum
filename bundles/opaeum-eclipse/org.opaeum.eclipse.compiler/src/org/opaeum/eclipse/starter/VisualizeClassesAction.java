package org.opaeum.eclipse.starter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.eclipse.newchild.IDiagramCreator;
import org.opaeum.eclipse.newchild.RelationshipDirection;

public class VisualizeClassesAction extends AbstractOpaeumAction{
	Set<Element> elements=new HashSet<Element>();
	public VisualizeClassesAction(IStructuredSelection selection){
		super(selection, "Visualize");
		Object[] array = selection.toArray();
		for(Object object:array){
			EObject e = EmfElementFinder.adaptObject(object);
			if(e instanceof Class){
				EList<Association> associations = ((Class)e).getAssociations();
				for(Association association:associations){
					elements.addAll(association.getEndTypes());
					elements.add(association);
					EList<Type> endTypes = association.getEndTypes();
					for(Type type:endTypes){
						EList<Association> associations2 = type.getAssociations();
						elements.addAll(associations2);
					}
				}
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
