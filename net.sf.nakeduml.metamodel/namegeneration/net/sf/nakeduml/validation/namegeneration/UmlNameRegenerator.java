package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteStructuralFeatureAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.name.NameConverter;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.model.IAssociationEnd;

/**
 * Regenerates and sets the (UML) name of an element. Also sets the name and
 * qualifiedUmlName on the elements mapping info
 * 
 */
@StepDependency(phase = NameGenerationPhase.class)
public class UmlNameRegenerator extends AbstractNameGenerator {
	@VisitBefore(matchSubclasses = true)
	public void updateUmlName(INakedElement nakedElement) {
		IMappingInfo mappingInfo = nakedElement.getMappingInfo();
		nakedElement.setName(generateUmlName(nakedElement).toString());
		mappingInfo.setQualifiedUmlName(generateQualifiedUmlName(nakedElement));
	}

	protected NameWrapper generateUmlName(INakedElement mew) {
		// Be null safe for algorithms that required non-containing elements for
		// name generation, i.e. actions
		String name = mew == null ? "null" : mew.getName();
		if (name != null && name.trim().length() == 0) {
			name = null;
		}
		if (mew instanceof INakedPackage) {
			if (mew.getName() == null) {
				name = "AnonymousPackage";
			} else {
				name = mew.getName();
			}
		} else if (mew instanceof INakedAssociation) {
			if (name == null) {
				INakedAssociation a = (INakedAssociation) mew;
				IAssociationEnd end1 = a.getEnd1();
				IAssociationEnd end2 = a.getEnd2();
				if (end1 == null || end2 == null) {
					name = "AnonymousAssociation";
				} else {
					if (end1.isComposite() || !end2.isNavigable()) {
						end1 = end2;
						end2 = a.getEnd1();
					}
					name = generateUmlName((INakedElement) end1).getCapped().getAsIs() + generateUmlName((INakedElement) end2).getCapped();
				}
			}
		} else if (mew instanceof INakedTypedElement) {
			name = generateTypedElementName(name, (INakedTypedElement) mew);
		} else if (mew instanceof INakedValueSpecification) {
			name = generateNameForValueSpecification(name, (INakedValueSpecification) mew);
		} else if (mew instanceof INakedTransition) {
			if (name == null) {
				INakedTransition t = (INakedTransition) mew;
				name = "to" + generateUmlName(t.getTarget()).getAsIs();
			}
		} else if (mew instanceof INakedActivityEdge) {
			if (name == null) {
				INakedActivityEdge t = (INakedActivityEdge) mew;
				name = "to" + generateUmlName(t.getEffectiveTarget()).getAsIs();
			}
		} else if (mew instanceof INakedState) {
			if (name == null) {
				INakedState state = (INakedState) mew;
				name = state.getKind().getName() + state.getMappingInfo().getNakedUmlId() + "In" + state.getContainer().getName();
			}
		} else if (mew instanceof INakedRegion) {
			if (name == null) {
				INakedRegion region = (INakedRegion) mew;
				if (region.getPeerRegions().size() == 0) {
					name = region.getNameSpace().getName() + "Region";
				} else {
					name = region.getNameSpace().getName() + "Region" + region.getMappingInfo().getNakedUmlId();
				}
			}
		} else if (mew instanceof INakedAction) {
			name = generateNameForAction(name, (INakedAction) mew);
		} else if (mew instanceof INakedGeneralization) {
			if (name == null) {
				name = "isA" + ((INakedGeneralization) mew).getGeneral().getName();
			}
		} else if (mew instanceof INakedComment) {
			if (name == null) {
				name = "Comment" + mew.hashCode();
			}
		}
		if (name == null) {
			name = mew.getMetaClass() + mew.getMappingInfo().getNakedUmlId();
		}
		name = name.replaceAll("[\\p{Punct}\\p{Space}]", "_");
		return new SingularNameWrapper(name, null);
	}

	public static void main(String[] args) {
		System.out.println("?_;'123  13f".replaceAll("[\\p{Punct}\\p{Space}]", "_"));
	}

	private String generateNameForValueSpecification(String in, INakedValueSpecification vs) {
		String name = in;
		if (name == null) {
			INakedElementOwner ownerElement = vs.getOwnerElement();
			if (ownerElement instanceof INakedTimeEvent) {
				name = "when";
			} else if (ownerElement instanceof INakedTransition) {
				name = "guardFor";
			} else if (ownerElement instanceof INakedActivityEdge) {
				INakedActivityEdge ae = (INakedActivityEdge) ownerElement;
				if (vs.equals(ae.getGuard())) {
					name = "guardFor";
				} else {
					name = "weightFor";
				}
			} else {
				name = "valueFor";
			}
			name = name + NameConverter.capitalize(((INakedElement) ownerElement).getName());
		}
		return name;
	}

	private String generateNameForAction(String in, INakedAction nakedAction) {
		String name = in;
		if (name == null) {
			if (nakedAction instanceof INakedCallAction) {
				INakedCallAction action = (INakedCallAction) nakedAction;
				name = "call" + generateUmlName(action.getCalledElement()).getCapped() + action.getMappingInfo().getNakedUmlId();
			} else if (nakedAction instanceof INakedStartClassifierBehaviorAction) {
				INakedStartClassifierBehaviorAction action = (INakedStartClassifierBehaviorAction) nakedAction;
				name = "start" + generateUmlName(action.getTarget()).getCapped() + action.getMappingInfo().getNakedUmlId();
			} else if (nakedAction instanceof INakedCreateObjectAction) {
				INakedCreateObjectAction action = (INakedCreateObjectAction) nakedAction;
				name = "create" + generateUmlName(action.getClassifier()).getCapped() + action.getMappingInfo().getNakedUmlId();
			} else if (nakedAction instanceof INakedSendSignalAction) {
				INakedSendSignalAction action = (INakedSendSignalAction) nakedAction;
				name = "send" + generateUmlName(action.getSignal()).getCapped() + action.getMappingInfo().getNakedUmlId();
			} else if (nakedAction instanceof INakedWriteStructuralFeatureAction) {
				INakedWriteStructuralFeatureAction action = (INakedWriteStructuralFeatureAction) nakedAction;
				name = "write" + generateUmlName(action.getFeature()).getCapped() + action.getMappingInfo().getNakedUmlId();
			} else if (nakedAction instanceof INakedAcceptEventAction) {
				INakedAcceptEventAction action = (INakedAcceptEventAction) nakedAction;
				if (action.getTrigger() == null) {
					name = "anonymousAcceptEventAction" + action.getMappingInfo().getNakedUmlId();
				} else {
					if (action.getTrigger().getEvent() instanceof NakedTimeEventImpl) {
						name = "waitFor" + generateUmlName(action.getTrigger().getEvent()) + action.getMappingInfo().getNakedUmlId();
					} else if (action.getTrigger().getEvent() instanceof INakedOperation) {
						name = "accept" + generateUmlName(action.getTrigger().getEvent()).getCapped()
								+ action.getMappingInfo().getNakedUmlId();
					} else if (action.getTrigger().getEvent() instanceof INakedSignal) {
						name = "accept" + generateUmlName(action.getTrigger().getEvent()).getCapped()
								+ action.getMappingInfo().getNakedUmlId();
					}
				}
			} else {
				name = "action" + nakedAction.getMappingInfo().getNakedUmlId();
			}
		}
		return name;
	}

	private String generateTypedElementName(String in, INakedTypedElement te) {
		String name = in;
		if (te instanceof INakedParameterNode) {
			// Ensure that paramterNodes and their parameters have the same name
			INakedParameterNode node = (INakedParameterNode) te;
			if (node.getParameter().isReturn()) {
				name = "result";// For OCL postconditions
			} else {
				name = node.getParameter().getName();
			}
		} else if (te instanceof INakedPin) {
			INakedPin node = (INakedPin) te;
			if (name == null) {
				if (node.getNakedBaseType() == null) {
					// Value pins can have null baseTypes
					name = "anonymousPin";
				} else {
					// Generate a unique name
					name = NameConverter.decapitalize(node.getNakedBaseType().getName() + node.getMappingInfo().getNakedUmlId());
				}
			}
		} else {
			if (name == null || (te.getNakedBaseType() != null && te.getName().equals(te.getNakedBaseType().getName()))) {
				// USe the type's name
				name = NameConverter.decapitalize(te.getNakedBaseType().getName());
			}
		}
		return name;
	}

	protected String generateQualifiedUmlName(INakedElement elem) {
		String generatedName;
		// Use nameSpace rather than elementOwner to ensure most unique name
		if (elem.getNameSpace() == null || (elem instanceof INakedPackage && ((INakedPackage) elem).isRootPackage())) {
			generatedName = elem.getName();
		} else {
			generatedName = generateQualifiedUmlName(elem.getNameSpace()) + "::" + elem.getName();
		}
		return generatedName;
	}
}
