package net.sf.nakeduml.emf.extraction;

import java.util.Collection;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitorAdapter;

import org.eclipse.uml2.uml.Element;

public class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace> {

	@Override
	public Collection<? extends Element> getChildren(Element root) {
		return root.getOwnedElements();
	}
}
