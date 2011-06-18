package org.nakeduml.topcased.propertysections.filters;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public abstract class AbstractFilter implements IFilter{
	public abstract boolean select(Element e);
	@Override
	public boolean select(Object toTest){
		Element element = null;
		if(toTest instanceof EMFGraphNodeEditPart){
			element = (Element) ((EMFGraphNodeEditPart) toTest).getEObject();
		}else if(toTest instanceof EMFGraphEdgeEditPart){
			element = (Element) ((EMFGraphEdgeEditPart) toTest).getEObject();
		}else if(toTest instanceof Element){
			element = (Element) toTest;
		}
		if(element != null){
			return select(element);
		}else{
			return false;
		}
	}
}
