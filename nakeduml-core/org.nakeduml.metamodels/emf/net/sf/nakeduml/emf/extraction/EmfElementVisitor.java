package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitorAdapter;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.nakeduml.eclipse.EmfElementFinder;

public abstract class EmfElementVisitor extends VisitorAdapter<Element,EmfWorkspace>{
	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Element> getChildren(Element root){
		Collection<Element> elements = EmfElementFinder.getCorrectOwnedElements(root);
		if(!(root instanceof EmfWorkspace)){
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
