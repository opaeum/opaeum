package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public abstract class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace>{
	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Element> getChildren(Element root){
		Collection<Element> elements = EmfElementFinder.getCorrectOwnedElements(root);
		if(!(root instanceof EmfWorkspace) && root.getEAnnotation(StereotypeNames.NUML_ANNOTATION)!=null){
			@SuppressWarnings("rawtypes")
			List contents = StereotypesHelper.getNumlAnnotation(root).getContents();
			elements.addAll((Collection<? extends Element>) contents);
		}
		if(root instanceof Classifier){
			Classifier c = (Classifier) root;
			for(Association association:c.getAssociations()){
				for(Property property:association.getNavigableOwnedEnds()){
					if(c.equals(property.getOtherEnd().getType())){
						elements.add(property);
					}
				}
			}
		}
		return elements;
	}
}
