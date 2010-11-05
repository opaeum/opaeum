package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitorAdapter;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StructuredActivityNode;

public class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace> {

	@Override
	public Collection<? extends Element> getChildren(Element root) {
		Collection<Element> elements  = new HashSet<Element>(root.getOwnedElements());
		if(root instanceof StructuredActivityNode){
			StructuredActivityNode node = (StructuredActivityNode) root;
			elements.addAll(node.getNodes());
			elements.addAll(node.getEdges());
		}
		return elements;
	}
}
