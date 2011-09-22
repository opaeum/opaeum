package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedHelper;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.internal.NakedCommentImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedInstanceSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedSlotImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.profiles.INakedStereotype;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;

/**
 * Applies stereotypes to elements. Uses InstanceSpecifications to represent stereotype applications Should be done last. pre: All
 * Stereotype classifiers, their attributes and their attributes types must be available
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
		ValueSpecificationExtractor.class,ConnectorExtractor.class,ImportExtractor.class
},after = {
		ConnectorExtractor.class,ValueSpecificationExtractor.class,ImportExtractor.class
})
public class StereotypeApplicationExtractor extends AbstractExtractorFromEmf{
	@VisitAfter
	public void visitContextualEvent(Trigger t){
		if(t.getEvent() instanceof TimeEvent && super.isDeadline((TimeEvent) t.getEvent())){
			// Do nothing - normal stereotype application logic will work
		}else if(t.getEvent() != null){
			// Events are duplicated and stored under the trigger referencing it and normal stereotype application logic won't find the
			// correct
			// naked originalElement
			INakedEvent nakedPeer = (INakedEvent) nakedWorkspace.getModelElement(getEventId(t));
			if(nakedPeer != null){
				addStereotypes(nakedPeer, t.getEvent());
				addKeywords(nakedPeer, t.getEvent());
			}
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	@VisitAfter(matchSubclasses = true)
	public void visit(Element element){
		NakedElementImpl nakedPeer = (NakedElementImpl) getNakedPeer(element);
		if(element instanceof Comment){
			visitComment((Comment) element);
		}
		if(nakedPeer != null){
			if(nakedPeer instanceof INakedHelper){
				System.out.println();
			}
			// Some element may not be supported by NakedUML
			addStereotypes(nakedPeer, element);
			addKeywords(nakedPeer, element);
		}
	}
	private void addKeywords(INakedElement nakedPeer,Element e){
		addKeywords(nakedPeer, e.getKeywords());
		addKeywords(nakedPeer, StereotypesHelper.getNumlAnnotation(e).getDetails().keySet());
	}
	public void addKeywords(INakedElement nakedPeer,Collection<String> keywords){
		for(String s:keywords){
			if(!(nakedPeer.hasStereotype(s) || s.equalsIgnoreCase("uuid"))){
				String id = nakedPeer.getId() + s;
				INakedInstanceSpecification is = (INakedInstanceSpecification) nakedWorkspace.getModelElement(id);
				if(is == null){
					is = new NakedInstanceSpecificationImpl();
					// No classifier - string classifier will be assigned during linkage
					is.initialize(id, s, false);
					nakedPeer.addStereotype(is);
					nakedWorkspace.putModelElement(is);
				}
			}
		}
	}
	private void visitComment(Comment a){
		INakedElement e = super.getNakedPeer(a.getOwner());
		if(e != null){
			INakedComment nakedComment = (INakedComment) getNakedPeer(a);
			if(nakedComment == null){
				nakedComment = new NakedCommentImpl();
				initialize(nakedComment, a, a.getOwner());
			}
			nakedComment.setBody(a.getBody());
			e.getComments().add(nakedComment);
		}
	}
	private void addStereotypes(INakedElement nakedElement,Element modelElement){
		Iterator<Stereotype> stereotypes = modelElement.getAppliedStereotypes().iterator();
		while(stereotypes.hasNext()){
			Stereotype stereotype = (Stereotype) stereotypes.next();
			INakedStereotype nakedStereotype = (INakedStereotype) getNakedPeer(stereotype);
			if(nakedStereotype != null){
				INakedInstanceSpecification instanceSpec = buildStereotypeApplication(modelElement, stereotype, nakedStereotype);
				if(nakedElement instanceof INakedElementOwner){
					instanceSpec.setOwnerElement(nakedElement);
				}
				nakedElement.addStereotype(instanceSpec);
			}
		}
	}
	@SuppressWarnings("unchecked")
	private INakedInstanceSpecification buildStereotypeApplication(Element modelElement,Stereotype stereotype,INakedStereotype nakedStereotype){
		String stereotypeApplicationId = getId(modelElement) + "#" + stereotype.getName();
		INakedInstanceSpecification instanceSpec = (INakedInstanceSpecification) nakedWorkspace.getModelElement(stereotypeApplicationId);
		if(instanceSpec == null){
			instanceSpec = new NakedInstanceSpecificationImpl();
			instanceSpec.initialize(stereotypeApplicationId, stereotype.getName(), false);
			nakedWorkspace.putModelElement(instanceSpec);
			instanceSpec.setClassifier(nakedStereotype);
		}
		EObject application = modelElement.getStereotypeApplication(stereotype);
		List<? extends INakedProperty> effectiveAttributes = nakedStereotype.getEffectiveAttributes();
		Iterator<? extends INakedProperty> attributes = effectiveAttributes.iterator();
		while(attributes.hasNext()){
			INakedProperty attribute = (INakedProperty) attributes.next();
			EStructuralFeature structuralFeature = application.eClass().getEStructuralFeature(attribute.getName());
			if(structuralFeature != null){
				// might be an "artificial" feature introduced by NakedUml
				Object value = application.eGet(structuralFeature);
				String id = attribute.getId() + "#" + stereotypeApplicationId;
				INakedSlot slot = (INakedSlot) nakedWorkspace.getModelElement(id);
				if(slot == null){
					slot = new NakedSlotImpl();
					slot.initialize(id, attribute.getName(), false);
					slot.setOwnerElement(instanceSpec);
					instanceSpec.addOwnedElement(slot);
					nakedWorkspace.putModelElement(slot);
				}
				slot.setDefiningFeature(attribute);
				if(value instanceof EList){
					EList<? extends EObject> values = (EList<? extends EObject>) value;
					Iterator<? extends EObject> iter = values.iterator();
					int i = 0;
					while(iter.hasNext()){
						putValue(i, iter.next(), slot);
						i++;
					}
				}else{
					putValue(0, value, slot);
				}
			}
		}
		return instanceSpec;
	}
	private void putValue(int index,Object value,INakedSlot slot){
		if(value != null && value.toString().trim().length() > 0){
			String id = slot.getId() + index;
			NakedValueSpecificationImpl valueSpec = (NakedValueSpecificationImpl) nakedWorkspace.getModelElement(id);
			if(valueSpec == null){
				valueSpec = new NakedValueSpecificationImpl();
				valueSpec.initialize(id, slot.getName(), false);
				valueSpec.setOwnerElement(slot);
				slot.addOwnedElement(valueSpec);
				nakedWorkspace.putModelElement(valueSpec);
			}
			if(value instanceof EModelElement){
				EModelElement eObjectValue = (EModelElement) value;
				String valueId = getId(eObjectValue);
				INakedElement nakedElementValue = nakedWorkspace.getModelElement(valueId);
				if(nakedElementValue == null){
					if(value instanceof EEnumLiteralImpl){
						if(slot.getDefiningFeature().getNakedBaseType() instanceof INakedEnumeration){
							INakedEnumeration ne = (INakedEnumeration) slot.getDefiningFeature().getNakedBaseType();
							valueSpec.setValue(ne.lookupLiteral(((EEnumLiteral) value).getLiteral()));
							// Note that it is NOT an originalElement reference. These enum literals
							// come from the profile, not
							// the model
						}else{
							valueSpec.setValue(((EEnumLiteral) value).getLiteral());
						}
					}else{
						// resolve later to the actual originalElement in the model that this refers
						// to
						valueSpec.setValueId(valueId);
					}
				}else{
					valueSpec.setValue(nakedElementValue);
				}
			}else{
				valueSpec.setValue(value);
			}
		}
	}
}
