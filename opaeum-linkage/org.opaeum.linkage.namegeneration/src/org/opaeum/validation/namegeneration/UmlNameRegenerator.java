package org.opaeum.validation.namegeneration;

import nl.klasse.octopus.model.IAssociationEnd;

import org.opaeum.feature.MappingInfo;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedComment;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.metamodel.statemachines.INakedRegion;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.name.NameConverter;

/**
 * Regenerates and sets the (UML) name of an originalElement. Also sets the name and qualifiedUmlName on the elements mapping info
 * 
 */
@StepDependency(phase = NameGenerationPhase.class)
public class UmlNameRegenerator extends AbstractNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateUmlName(INakedElement nakedElement){
		MappingInfo mappingInfo = nakedElement.getMappingInfo();
		nakedElement.setName(generateUmlName(nakedElement).toString());
		mappingInfo.setQualifiedUmlName(generateQualifiedUmlName(nakedElement));
		if(nakedElement instanceof InverseArtificialProperty){
			InverseArtificialProperty ap = (InverseArtificialProperty) nakedElement;
			if(ap.getMultiplicity().isMany()){
				ap.setName(NameConverter.decapitalize(ap.getNakedBaseType().getName()));
			}
		}
		if(nakedElement instanceof ICompositionParticipant && ((ICompositionParticipant) nakedElement).getEndToComposite() instanceof InverseArtificialProperty){
			InverseArtificialProperty ap = (InverseArtificialProperty) ((ICompositionParticipant) nakedElement).getEndToComposite();
			INakedProperty oe = ap.getOtherEnd();
			if(oe instanceof InverseArtificialProperty && oe.getNakedMultiplicity().isMany()){
				oe.setName(NameConverter.decapitalize(oe.getNakedBaseType().getName()));
				getAffectedElements().add(ap);
			}
		}
	}
	protected NameWrapper generateUmlName(INakedElement mew){
		// Be null safe for algorithms that required non-containing elements for
		// name generation, i.e. actions
		String name = mew == null ? "null" : mew.getName();
		if(name != null && name.trim().length() == 0){
			name = null;
		}
		if(mew instanceof INakedPackage){
			if(mew.getName() == null){
				name = "AnonymousPackage";
			}else{
				name = mew.getName();
			}
		}else if(mew instanceof INakedAssociation){
			if(name == null){
				INakedAssociation a = (INakedAssociation) mew;
				IAssociationEnd end1 = a.getEnd1();
				IAssociationEnd end2 = a.getEnd2();
				if(end1 == null || end2 == null){
					name = "AnonymousAssociation";
				}else{
					if(end1.isComposite() || !end2.isNavigable()){
						end1 = end2;
						end2 = a.getEnd1();
					}
					name = generateUmlName((INakedElement) end1).getCapped().getAsIs() + generateUmlName((INakedElement) end2).getCapped();
				}
			}
		}else if(mew instanceof INakedMultiplicityElement){
			name = generateTypedElementName(name, (INakedTypedElement) mew);
		}else if(mew instanceof INakedValueSpecification){
			name = generateNameForValueSpecification(name, (INakedValueSpecification) mew);
		}else if(mew instanceof INakedTransition){
			if(name == null){
				INakedTransition t = (INakedTransition) mew;
				name = "to" + generateUmlName(t.getTarget()).getAsIs();
			}
		}else if(mew instanceof INakedActivityEdge){
			if(name == null){
				INakedActivityEdge t = (INakedActivityEdge) mew;
				name = "to" + generateUmlName(t.getEffectiveTarget()).getAsIs();
			}
		}else if(mew instanceof INakedState){
			if(name == null){
				INakedState state = (INakedState) mew;
				name = state.getKind().getName() + state.getMappingInfo().getOpaeumId() + "In" + state.getContainer().getName();
			}
		}else if(mew instanceof INakedRegion){
			if(name == null){
				INakedRegion region = (INakedRegion) mew;
				if(region.getPeerRegions().size() == 0){
					name = region.getNameSpace().getName() + "Region";
				}else{
					name = region.getNameSpace().getName() + "Region" + region.getMappingInfo().getOpaeumId();
				}
			}
		}else if(mew instanceof INakedAction){
			name = generateNameForAction(name, (INakedAction) mew);
		}else if(mew instanceof INakedGeneralization){
			if(name == null){
				name = "isA" + ((INakedGeneralization) mew).getGeneral().getName();
			}
		}else if(mew instanceof INakedComment){
			if(name == null){
				name = "Comment" + mew.hashCode();
			}
		}
		if(name == null){
			if(mew==null || mew.getMappingInfo()==null){
				System.out.println();
			}
			name = mew.getMetaClass() + mew.getMappingInfo().getOpaeumId();
		}
		return new SingularNameWrapper(name, null);
	}
	public static void main(String[] args){
		System.out.println("?_;':123  13f-".replaceAll("[\\p{Punct}\\p{Space}]", "_"));
	}
	private String generateNameForValueSpecification(String in,INakedValueSpecification vs){
		String name = in;
		if(name == null){
			INakedElementOwner ownerElement = vs.getOwnerElement();
			if(ownerElement instanceof INakedTimeEvent){
				name = "when";
			}else if(ownerElement instanceof INakedTransition){
				name = "guardFor";
			}else if(ownerElement instanceof INakedActivityEdge){
				INakedActivityEdge ae = (INakedActivityEdge) ownerElement;
				if(vs.equals(ae.getGuard())){
					name = "guardFor";
				}else{
					name = "weightFor";
				}
			}else{
				name = "valueFor";
			}
			name = name + NameConverter.capitalize(((INakedElement) ownerElement).getName());
		}
		return name;
	}
	private String generateNameForAction(String in,INakedAction nakedAction){
		String name = in;
		if(name == null){
			if(nakedAction instanceof INakedCallAction){
				INakedCallAction action = (INakedCallAction) nakedAction;
				name = "call" + generateUmlName(action.getCalledElement()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof INakedStartClassifierBehaviorAction){
				INakedStartClassifierBehaviorAction action = (INakedStartClassifierBehaviorAction) nakedAction;
				name = "start" + generateUmlName(action.getTarget()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof INakedCreateObjectAction){
				INakedCreateObjectAction action = (INakedCreateObjectAction) nakedAction;
				name = "create" + generateUmlName(action.getClassifier()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof INakedSendSignalAction){
				INakedSendSignalAction action = (INakedSendSignalAction) nakedAction;
				name = "send" + generateUmlName(action.getSignal()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof INakedWriteStructuralFeatureAction){
				INakedWriteStructuralFeatureAction action = (INakedWriteStructuralFeatureAction) nakedAction;
				name = "write" + generateUmlName(action.getFeature()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof INakedAcceptEventAction){
				INakedAcceptEventAction action = (INakedAcceptEventAction) nakedAction;
				if(action.getTriggers().isEmpty()){
					name = "anonymousAcceptEventAction" + action.getMappingInfo().getOpaeumId();
				}else{
					INakedTrigger trigger = action.getTriggers().iterator().next();
					if(trigger.getEvent() instanceof NakedTimeEventImpl){
						name = "waitFor" + generateUmlName(trigger.getEvent()) + action.getMappingInfo().getOpaeumId();
					}else if(trigger.getEvent() instanceof INakedOperation){
						name = "accept" + generateUmlName(trigger.getEvent()).getCapped() + action.getMappingInfo().getOpaeumId();
					}else if(trigger.getEvent() instanceof INakedSignal){
						name = "accept" + generateUmlName(trigger.getEvent()).getCapped() + action.getMappingInfo().getOpaeumId();
					}
				}
			}else{
				name = "action" + nakedAction.getMappingInfo().getOpaeumId();
			}
		}
		return name;
	}
	protected String generateTypedElementName(String in,INakedTypedElement te){
		String name = in;
		if(te instanceof INakedParameterNode){
			// Ensure that paramterNodes and their parameters have the same name
			INakedParameterNode node = (INakedParameterNode) te;
			if(node.getParameter() != null){
				if(node.getParameter().isReturn()){
					name = "result";// For OCL postconditions
				}else{
					name = node.getParameter().getName();
				}
			}
		}else if(te instanceof INakedPin){
			INakedPin node = (INakedPin) te;
			if(name == null){
				if(node.getNakedBaseType() == null){
					// Value pins can have null baseTypes
					name = "anonymousPin";
				}else{
					// Generate a unique name
					name = NameConverter.decapitalize(node.getNakedBaseType().getName() + node.getMappingInfo().getOpaeumId());
				}
			}
		}else{
			if(name == null || (te.getNakedBaseType() != null && te.getName().equals(te.getNakedBaseType().getName()))){
				// USe the type's name
				name = NameConverter.decapitalize(te.getNakedBaseType().getName());
			}
		}
		return name;
	}
	protected String generateQualifiedUmlName(INakedElement elem){
		String generatedName;
		// Use nameSpace rather than elementOwner to ensure most unique name
		if(elem.getNameSpace() == null || (elem instanceof INakedPackage && ((INakedPackage) elem).isRootPackage())){
			generatedName = elem.getName();
		}else{
			generatedName = generateQualifiedUmlName(elem.getNameSpace()) + "::" + elem.getName();
		}
		return generatedName;
	}
	@Override
	protected boolean hasName(INakedElement p){
		return p.getName()!=null && p.getName().trim().length()>0;
	}
}
