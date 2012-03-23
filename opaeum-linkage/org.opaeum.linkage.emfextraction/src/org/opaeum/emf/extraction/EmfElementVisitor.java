package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Activity;
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
	public Collection<Element> getChildren(Element root){
		return getChildrenAndRetryIfFailed(root, 0);
	}
	protected Collection<Element> getChildrenAndRetryIfFailed(Element root,int count){
		Collection<Element> elements = EmfElementFinder.getCorrectOwnedElements(root);
		if(!(root instanceof EmfWorkspace) && root.getEAnnotation(StereotypeNames.NUML_ANNOTATION) != null){
			@SuppressWarnings("rawtypes")
			List contents = StereotypesHelper.getNumlAnnotation(root).getContents();
			elements.addAll((Collection<? extends Element>) contents);
		}
		try{
			if(root instanceof Classifier){
				Classifier c = (Classifier) root;
				for(Association association:c.getAssociations()){
					for(Property property:association.getNavigableOwnedEnds()){
						if(property.getOtherEnd() != null && c.equals(property.getOtherEnd().getType())){
							// property.getOtherEnd() could be null during deletion
							elements.add(property);
						}
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			if(count < 5){
				try{
					Thread.sleep(2000);
				}catch(InterruptedException e1){
				}
				return getChildrenAndRetryIfFailed(root, ++count);
			}
			// HACK weird bug in:
			// org.eclipse.emf.ecore.util.ECrossReferenceAdapter.getInverseReferences(ECrossReferenceAdapter.java:332)
		}
		return elements;
	}
}
