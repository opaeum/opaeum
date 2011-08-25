package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitorAdapter;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.ValuePin;

public class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace>{
	@Override
	public Collection<? extends Element> getChildren(Element root){
		Collection<Element> elements = new HashSet<Element>(root.getOwnedElements());
		// Unimplemented containment features, oy
		if(root instanceof StructuredActivityNode){
			StructuredActivityNode node = (StructuredActivityNode) root;
			elements.addAll(node.getNodes());
			elements.addAll(node.getEdges());
		}else if(root instanceof Transition){
			Transition t = (Transition) root;
			elements.addAll(t.getTriggers());
		}else if(root instanceof AcceptEventAction){
			elements.addAll(((AcceptEventAction) root).getTriggers());
		}else if(root instanceof ValuePin){
			ValuePin vp = (ValuePin) root;
			if(vp.getValue() != null){
				elements.add(vp.getValue());
			}
		}else if(root instanceof ActivityEdge){
			ActivityEdge e = (ActivityEdge) root;
			if(e.getGuard() != null){
				elements.add(e.getGuard());
			}
			if(e.getWeight() != null){
				elements.add(e.getWeight());
			}
		}
		if(!(root instanceof EmfWorkspace)){
			List contents = StereotypesHelper.getNumlAnnotation(root).getContents();
			elements.addAll((Collection<? extends Element>) contents);
		}
		return elements;
	}
}
