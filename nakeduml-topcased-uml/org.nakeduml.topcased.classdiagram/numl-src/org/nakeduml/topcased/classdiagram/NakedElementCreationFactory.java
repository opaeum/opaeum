package org.nakeduml.topcased.classdiagram;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
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
		if(element instanceof NamedElement){
			NamedElement ne=(NamedElement) element;
			ne.setName(keyword);
		}
		EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
		ann.getDetails().put(keyword, "");
		return element;
	}
}