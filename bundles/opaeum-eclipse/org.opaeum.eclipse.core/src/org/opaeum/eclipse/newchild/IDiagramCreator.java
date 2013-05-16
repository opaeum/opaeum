package org.opaeum.eclipse.newchild;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Element;

public interface IDiagramCreator{
	public boolean matches(IEditorPart editor);
	public void createDiagram(String suffix, Collection<? extends Element> elements, IEditorPart editor, RelationshipDirection direction, EClass ...relationships);
}
