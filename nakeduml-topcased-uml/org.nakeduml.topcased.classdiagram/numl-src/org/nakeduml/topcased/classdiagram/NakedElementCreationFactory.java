package org.nakeduml.topcased.classdiagram;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;

public final class NakedElementCreationFactory extends GraphElementCreationFactory{
	private String keyword;
	public NakedElementCreationFactory(ICreationUtils cu, EClass eClass,String keyword){
		super(cu, eClass, "default");
		this.keyword = keyword;
	}
	@Override
	public EObject getNewModelObject(){
		Element element = (Element) super.getNewModelObject();
		EAnnotation ann = element.createEAnnotation(StereotypeNames.NUML_ANNOTATION);
		ann.getDetails().put(keyword, "");
		return element;
	}
}