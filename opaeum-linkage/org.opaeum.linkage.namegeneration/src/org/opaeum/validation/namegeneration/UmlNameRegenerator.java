package org.opaeum.validation.namegeneration;

import nl.klasse.octopus.model.IAssociationEnd;

import org.eclipse.uml2.uml.ICompositionParticipant;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementOwner;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterNode;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.WriteStructuralFeatureAction;
import org.opaeum.feature.MappingInfo;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.name.NameConverter;

/**
 * Regenerates and sets the (UML) name of an originalElement. Also sets the name and qualifiedUmlName on the elements mapping info
 * 
 */
@StepDependency(phase = NameGenerationPhase.class)
public class UmlNameRegenerator extends AbstractNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateUmlName(Element nakedElement){
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
			Property oe = ap.getOtherEnd();
			if(oe instanceof InverseArtificialProperty && oe.getNakedMultiplicity().isMany()){
				oe.setName(NameConverter.decapitalize(oe.getNakedBaseType().getName()));
				getAffectedElements().add(ap);
			}
		}
	}
	protected NameWrapper generateUmlName(Element mew){
		// Be null safe for algorithms that required non-containing elements for
		// name generation, i.e. actions
		String name = mew == null ? "null" : mew.getName();
		if(name != null && name.trim().length() == 0){
			name = null;
		}
		if(mew instanceof Package){
			if(mew.getName() == null){
				name = "AnonymousPackage";
			}else{
				name = mew.getName();
			}
		}else if(mew instanceof Association){
			if(name == null){
				Association a = (Association) mew;
				IAssociationEnd end1 = a.getEnd1();
				IAssociationEnd end2 = a.getEnd2();
				if(end1 == null || end2 == null){
					name = "AnonymousAssociation";
				}else{
					if(end1.isComposite() || !end2.isNavigable()){
						end1 = end2;
						end2 = a.getEnd1();
					}
					name = generateUmlName((Element) end1).getCapped().getAsIs() + generateUmlName((Element) end2).getCapped();
				}
			}
		}else if(mew instanceof MultiplicityElement){
			name = generateTypedElementName(name, (TypedElement) mew);
		}else if(mew instanceof ValueSpecification){
			name = generateNameForValueSpecification(name, (ValueSpecification) mew);
		}else if(mew instanceof Transition){
			if(name == null){
				Transition t = (Transition) mew;
				name = "to" + generateUmlName(t.getTarget()).getAsIs();
			}
		}else if(mew instanceof ActivityEdge){
			if(name == null){
				ActivityEdge t = (ActivityEdge) mew;
				name = "to" + generateUmlName(t.getEffectiveTarget()).getAsIs();
			}
		}else if(mew instanceof State){
			if(name == null){
				State state = (State) mew;
				name = state.getKind().getName() + state.getMappingInfo().getOpaeumId() + "In" + state.getContainer().getName();
			}
		}else if(mew instanceof Region){
			if(name == null){
				Region region = (Region) mew;
				if(region.getPeerRegions().size() == 0){
					name = region.getNameSpace().getName() + "Region";
				}else{
					name = region.getNameSpace().getName() + "Region" + region.getMappingInfo().getOpaeumId();
				}
			}
		}else if(mew instanceof Action){
			name = generateNameForAction(name, (Action) mew);
		}else if(mew instanceof Generalization){
			if(name == null){
				name = "isA" + ((Generalization) mew).getGeneral().getName();
			}
		}else if(mew instanceof Comment){
			if(name == null){
				name = "Comment" + mew.hashCode();
			}
		}
		if(name == null){
			name = mew.getMetaClass() + mew.getMappingInfo().getOpaeumId();
		}
		return new SingularNameWrapper(name, null);
	}
	public static void main(String[] args){
		System.out.println("?_;':123  13f-".replaceAll("[\\p{Punct}\\p{Space}]", "_"));
	}
	private String generateNameForValueSpecification(String in,ValueSpecification vs){
		String name = in;
		if(name == null){
			ElementOwner ownerElement = vs.getOwnerElement();
			if(ownerElement instanceof TimeEvent){
				name = "when";
			}else if(ownerElement instanceof Transition){
				name = "guardFor";
			}else if(ownerElement instanceof ActivityEdge){
				ActivityEdge ae = (ActivityEdge) ownerElement;
				if(vs.equals(ae.getGuard())){
					name = "guardFor";
				}else{
					name = "weightFor";
				}
			}else{
				name = "valueFor";
			}
			name = name + NameConverter.capitalize(((Element) ownerElement).getName());
		}
		return name;
	}
	private String generateNameForAction(String in,Action nakedAction){
		String name = in;
		if(name == null){
			if(nakedAction instanceof CallAction){
				CallAction action = (CallAction) nakedAction;
				name = "call" + generateUmlName(action.getCalledElement()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof StartClassifierBehaviorAction){
				StartClassifierBehaviorAction action = (StartClassifierBehaviorAction) nakedAction;
				name = "start" + generateUmlName(action.getTarget()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof CreateObjectAction){
				CreateObjectAction action = (CreateObjectAction) nakedAction;
				name = "create" + generateUmlName(action.getClassifier()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof SendSignalAction){
				SendSignalAction action = (SendSignalAction) nakedAction;
				name = "send" + generateUmlName(action.getSignal()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof WriteStructuralFeatureAction){
				WriteStructuralFeatureAction action = (WriteStructuralFeatureAction) nakedAction;
				name = "write" + generateUmlName(action.getFeature()).getCapped() + action.getMappingInfo().getOpaeumId();
			}else if(nakedAction instanceof AcceptEventAction){
				AcceptEventAction action = (AcceptEventAction) nakedAction;
				if(action.getTriggers().isEmpty()){
					name = "anonymousAcceptEventAction" + action.getMappingInfo().getOpaeumId();
				}else{
					Trigger trigger = action.getTriggers().iterator().next();
					if(trigger.getEvent() instanceof NakedTimeEventImpl){
						name = "waitFor" + generateUmlName(trigger.getEvent()) + action.getMappingInfo().getOpaeumId();
					}else if(trigger.getEvent() instanceof Operation){
						name = "accept" + generateUmlName(trigger.getEvent()).getCapped() + action.getMappingInfo().getOpaeumId();
					}else if(trigger.getEvent() instanceof Signal){
						name = "accept" + generateUmlName(trigger.getEvent()).getCapped() + action.getMappingInfo().getOpaeumId();
					}
				}
			}else{
				name = "action" + nakedAction.getMappingInfo().getOpaeumId();
			}
		}
		return name;
	}
	protected String generateTypedElementName(String in,TypedElement te){
		String name = in;
		if(te instanceof ParameterNode){
			// Ensure that paramterNodes and their parameters have the same name
			ParameterNode node = (ParameterNode) te;
			if(node.getParameter() != null){
				if(node.getParameter().isReturn()){
					name = "result";// For OCL postconditions
				}else{
					name = node.getParameter().getName();
				}
			}
		}else if(te instanceof Pin){
			Pin node = (Pin) te;
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
	protected String generateQualifiedUmlName(Element elem){
		String generatedName;
		// Use nameSpace rather than elementOwner to ensure most unique name
		if(elem.getNameSpace() == null || (elem instanceof Package && ((Package) elem).isRootPackage())){
			generatedName = elem.getName();
		}else{
			generatedName = generateQualifiedUmlName(elem.getNameSpace()) + "::" + elem.getName();
		}
		return generatedName;
	}
	@Override
	protected boolean hasName(Element p){
		return p.getName()!=null && p.getName().trim().length()>0;
	}
}
