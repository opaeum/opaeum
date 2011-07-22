package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitorAdapter;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;

public class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace> {

	@Override
	public Collection<? extends Element> getChildren(Element root) {
		Collection<Element> elements  = new HashSet<Element>(root.getOwnedElements());
		if(root instanceof StructuredActivityNode){
			StructuredActivityNode node = (StructuredActivityNode) root;
			elements.addAll(node.getNodes());
			elements.addAll(node.getEdges());
		}else if(root instanceof Transition){
			elements.addAll(((Transition) root).getTriggers());
		}else if (root instanceof AcceptEventAction){
			elements.addAll(((AcceptEventAction) root).getTriggers());
		}
		List  contents = StereotypesHelper.getNumlAnnotation(root).getContents();
		elements.addAll((Collection<? extends Element>) contents);
		return elements;
	}
}
