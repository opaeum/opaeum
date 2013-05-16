package org.opaeum.eclipse.starter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.eclipse.newchild.IDiagramCreator;
import org.opaeum.eclipse.newchild.RelationshipDirection;

public class VisualizePackageDependenciesAction extends AbstractOpaeumAction{
	Set<Element> elements=new HashSet<Element>();
	public VisualizePackageDependenciesAction(IStructuredSelection selection){
		super(selection, "Visualize Package Dependencies");
		Object[] array = selection.toArray();
		for(Object object:array){
			EObject e = EmfElementFinder.adaptObject(object);
			if(e instanceof Package){
				elements.add((Package) e);
			}
		}
	}
	@Override
	public void run(){
		Set<IDiagramCreator> diagramCreators = OpaeumEclipsePlugin.getDefault().getDiagramCreators();
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		for(IDiagramCreator c:diagramCreators){
			if(c.matches(activeEditor)){
				c.createDiagram( " Package Dependencies", elements, activeEditor, RelationshipDirection.BOTH, UMLPackage.eINSTANCE.getPackageImport());
			}
		}
	}
}
