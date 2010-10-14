package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.DISPLAY_INDEX;
import static net.sf.nakeduml.uigeneration.StereotypeNames.OPERATION_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PARAMETER_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_GROUP;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_KIND;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PROPERTY_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.RESULTING_USER_INTERACTION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_EDIT;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_VIEW;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationIn;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationInGroup;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.domainmetamodel.DomainOperation;
import net.sf.nakeduml.domainmetamodel.DomainParameter;
import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.OperationParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.ParameterField;
import net.sf.nakeduml.userinteractionmetamodel.ParameterNavigation;
import net.sf.nakeduml.userinteractionmetamodel.ParticipationGroup;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipation;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import nl.klasse.octopus.model.ParameterDirectionKind;

@StepDependency(phase = UserInteractionTransformationPhase.class, requires = { OperationUserInteractionCreator.class, ParticipationGroupCreator.class }, after = OperationUserInteractionCreator.class)
public class ParticipationCreator extends AbstractParticipationCreator {

	@VisitAfter
	public void visitEntity(INakedEntity c) {
		SortedMap<Double, INakedProperty> taggedProperties = new TreeMap<Double, INakedProperty>();
		int listCount = config.getNumberOfColumns();
		if (hasUserInteractions(c)) {

			INakedInstanceSpecification is = findInstanceSpecificationFor(entryModel, c);
			if (is != null) {
				List<INakedSlot> slots = is.getSlots();
				for (INakedSlot nakedSlot : slots) {
					Double d = (Double) getTag(nakedSlot, participationIn(UserInteractionKind.LIST), DISPLAY_INDEX);
					taggedProperties.put(d, nakedSlot.getDefiningFeature());
				}
			}

			for (INakedProperty property : c.getEffectiveAttributes()) {
				
				if (!property.getNakedBaseType().getIsAbstract()) {
					AbstractUserInteractionFolder entityFolder = findFolderFor((INakedClassifier) c);
					Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
					for (ClassifierUserInteraction eui : entityUserInteractions) {
						if (!eui.isCustom()) {
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
							PropertyParticipationKindMap kindMap = new PropertyParticipationKindMap(property, eui);
							TypedElementParticipationKind kind = kindMap.calculateParticipationKind();
							if (eui.getUserInteractionKind() == UserInteractionKind.LIST) {
								if (kindMap.isTagged() && kind != TypedElementParticipationKind.HIDDEN && !taggedProperties.containsValue(property)) {
									taggedProperties.put((Double) getTag(property, participationIn(UserInteractionKind.LIST), DISPLAY_INDEX), property);
								}
							} else {
								populatePropertyParticipation(property, eui, map, kind, null);
							}
						}
					}
				}
				
			}
		}

		for (Double displayIndex : taggedProperties.keySet()) {
			INakedProperty property = taggedProperties.get(displayIndex);
			AbstractUserInteractionFolder entityFolder = findFolderFor((INakedClassifier) c);
			Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
			for (ClassifierUserInteraction eui : entityUserInteractions) {
				if (!eui.isCustom() && eui.getUserInteractionKind() == UserInteractionKind.LIST) {
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
					PropertyParticipationKindMap kindMap = new PropertyParticipationKindMap(property, eui);
					TypedElementParticipationKind kind = kindMap.calculateParticipationKind();
					--listCount;
					if (listCount > 0) {
						populatePropertyParticipation(property, eui, map, kind, displayIndex);
					}
				}
			}
		}

		for (INakedProperty property : c.getEffectiveAttributes()) {
			
			
			if (hasUserInteractions(c) && !property.getNakedBaseType().getIsAbstract()) {
				AbstractUserInteractionFolder entityFolder = findFolderFor((INakedClassifier) c);
				Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
				for (ClassifierUserInteraction eui : entityUserInteractions) {
					if (!eui.isCustom() && eui.getUserInteractionKind() == UserInteractionKind.LIST) {
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
						PropertyParticipationKindMap kindMap = new PropertyParticipationKindMap(property, eui);
						TypedElementParticipationKind kind = kindMap.calculateParticipationKind();

						if (!taggedProperties.containsValue(property) && listCount > 0 && kind != TypedElementParticipationKind.HIDDEN) {
							if (!property.isComposite()) {
								--listCount;
							}
							populatePropertyParticipation(property, eui, map, kind, null);
						}
					}
				}
			}
		}
	}

	private void populatePropertyParticipation(INakedProperty property, ClassifierUserInteraction eui, NakedStructuralFeatureMap map,
			TypedElementParticipationKind kind, Number displayIndex) {
		if (kind == TypedElementParticipationKind.NAVIGATION) {
			populatePropertyNavigation(property, eui, map, kind, displayIndex);
			// Some navigation are also updateable
			if (kind == TypedElementParticipationKind.NAVIGATION && (property.getNakedMultiplicity().getUpper() == 1 && property.isRequired())
					&& !property.isComposite()) {
				kind = TypedElementParticipationKind.REQUIRED;
				populatePropertyField(property, eui, kind, displayIndex);
			}
		} else {
			populatePropertyField(property, eui, kind, displayIndex);
		}
	}

	private void populatePropertyField(INakedProperty property, ClassifierUserInteraction eui, TypedElementParticipationKind kind, Number displayIndex) {
		TypedElementParticipation pp;
		PropertyField result = new PropertyField();
		eui.addToOwnedPropertyField(result);
		pp = result;
		pp.setParticipationKind(kind);
		populatePropertyParticipation(eui, property, pp, displayIndex);
	}

	private void populatePropertyNavigation(INakedProperty property, ClassifierUserInteraction eui, NakedStructuralFeatureMap map,
			TypedElementParticipationKind kind, Number displayIndex) {
		
		TypedElementParticipation pp;
		PropertyNavigation participation = new PropertyNavigation();
		eui.addToOwnedPropertyNavigation(participation);
		participation.setResultingUserInteraction(findUserinteraction(property, map, (INakedInstanceSpecification) getTag(property, PROPERTY_SPECIFICATION,
				RESULTING_USER_INTERACTION)));
		
		pp = participation;
		pp.setParticipationKind(kind);
		populatePropertyParticipation(eui, property, pp, displayIndex);
		
		INakedInstanceSpecification tag = getTag(property, participationInGroup(UserInteractionKind.EDIT), PARTICIPATION_GROUP);
		if (tag!=null) {
			Set<ParticipationGroup> participationGroups = eui.getParticipationGroup();
			for (ParticipationGroup participationGroup : participationGroups) {
				if (participationGroup.getName().equals(tag.getName())) {
					participationGroup.addToPropertyNavigation(participation);
				}
			}
		} else {
			Set<ParticipationGroup> participationGroups = eui.getParticipationGroup();
			for (ParticipationGroup participationGroup : participationGroups) {
				if (participationGroup.getName().equals(eui.getFolder().getName())) {
					participationGroup.addToPropertyNavigation(participation);
				}
			}			
		}
		
		boolean group = calculateNavigationSecurityGroup(property);
		boolean user = calculateNavigationSecurityUser(property);
		createSecurityOnViewAction(property, participation, group, user);
	}

	private boolean calculateNavigationSecurityUser(INakedProperty property) {
		if (securityOnViewUser(property) && securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewUser(property) && !securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return false;
		} else if (!securityOnViewUser(property) && !securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && !securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewUser(property) && securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && !securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewUser(property) && !securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewUser(property) && !securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewUser(property) && securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewUser(property) && !securityOnEditUser(property) && securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewUser(property) && securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && securityOnEditUser(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewUser(property) && !securityOnEditUser(property) && !securityOnViewUser(property.getNakedBaseType()) && !securityOnEditUser(property.getNakedBaseType())) {
			return false;
		} else {
			throw new IllegalStateException();
		}
	}
	private boolean calculateNavigationSecurityGroup(INakedProperty property) {
		if (property.isComposite()) {
			return false;
		}
		if (securityOnViewGroup(property) && securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewGroup(property) && !securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (!securityOnViewGroup(property) && securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (!securityOnViewGroup(property) && !securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (!securityOnViewGroup(property) && !securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewGroup(property) && securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewGroup(property) && securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewGroup(property) && !securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewGroup(property) && !securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (securityOnViewGroup(property) && !securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (!securityOnViewGroup(property) && securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewGroup(property) && securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewGroup(property) && securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewGroup(property) && !securityOnEditGroup(property) && securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else if (securityOnViewGroup(property) && securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && securityOnEditGroup(property.getNakedBaseType())) {
			return true;
		} else if (!securityOnViewGroup(property) && !securityOnEditGroup(property) && !securityOnViewGroup(property.getNakedBaseType()) && !securityOnEditGroup(property.getNakedBaseType())) {
			return false;
		} else {
			throw new IllegalStateException();
		}
	}
	
	private void createSecurityOnViewAction(INakedProperty property, PropertyNavigation dp, boolean group, boolean user) {
		SecurityOnUserAction viewSecureUserAction = super.createSecureUserAction(property, SECURITY_ON_VIEW);
		viewSecureUserAction.setRequiresGroupOwnership(group);
		viewSecureUserAction.setRequiresUserOwnership(user);
		dp.setAdditionalSecurityOnView(viewSecureUserAction);
	}
	
	private boolean securityOnViewGroup(INakedElement e) {
		if (e.getStereotype(SECURITY_ON_VIEW)==null) {
			return true;
		} else {
			return super.createSecureUserAction(e, SECURITY_ON_VIEW).getRequiresGroupOwnership();
		}
	}
	private boolean securityOnViewUser(INakedElement e) {
		if (e.getStereotype(SECURITY_ON_VIEW)==null) {
			return false;
		} else {
			return super.createSecureUserAction(e, SECURITY_ON_VIEW).getRequiresUserOwnership();			
		}
	}
	private boolean securityOnEditGroup(INakedElement e) {
		if (e.getStereotype(SECURITY_ON_EDIT)==null) {
			return true;
		} else {
			return super.createSecureUserAction(e, SECURITY_ON_EDIT).getRequiresGroupOwnership();
		}
	}
	private boolean securityOnEditUser(INakedElement e) {
		if (e.getStereotype(SECURITY_ON_EDIT)==null) {
			return false;
		} else {
			return super.createSecureUserAction(e, SECURITY_ON_EDIT).getRequiresUserOwnership();			
		}
	}	
	
	@VisitBefore
	public void visitOperation(INakedOperation operation) {
		if (hasUserInteractions(operation.getOwner())) {
			AbstractUserInteractionFolder entityFolder = findFolderFor(operation.getOwner());
			Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
			for (ClassifierUserInteraction eui : entityUserInteractions) {
				createOperationNavigation(eui, operation);
			}
		}
	}

	private OperationNavigation createOperationNavigation(ClassifierUserInteraction eui, INakedOperation operation) {
		OperationNavigation result = new OperationNavigation();
		result.setName(getDomainNameOf(operation));
		OperatonParticipationKindMap kindMap = new OperatonParticipationKindMap(operation, eui);
		OperationParticipationKind kind = kindMap.calculateParticipationKind();
		result.setParticipationKind(kind);
		DomainClassifier entity = eui.getClassifier();
		result.setRepresentedElement(entity.findOwnedElement(getDomainNameOf(operation)));
		eui.addToOwnedOperationNavigation(result);
		Number displayIndex = getTag(operation, participationIn(eui.getUserInteractionKind()), DISPLAY_INDEX);
		if (displayIndex == null) {
			displayIndex = getTag(operation, OPERATION_SPECIFICATION, DISPLAY_INDEX);
			if (displayIndex == null) {
				// TODO a bit more intelligence in calculating the index
				displayIndex = 0;
			}
		}
		result.setDisplayIndex(displayIndex.doubleValue());
		String resultingUserInteractionName = getUserInteractionFQNameOf(operation, operation.isQuery() ? OperationUserInteractionKind.QUERY
				: OperationUserInteractionKind.INVOKE);
		result.setOperationUserInteraction((OperationUserInteraction) eui.getFolder().findOwnedElement(resultingUserInteractionName));
		
		INakedInstanceSpecification tag = getTag(operation, participationInGroup(UserInteractionKind.LIST), PARTICIPATION_GROUP);
		if (tag!=null) {
			Set<ParticipationGroup> participationGroups = eui.getParticipationGroup();
			for (ParticipationGroup participationGroup : participationGroups) {
				if (participationGroup.getName().equals(tag.getName())) {
					participationGroup.addToOperationNavigation(result);
				}
			}
		} else {
			Set<ParticipationGroup> participationGroups = eui.getParticipationGroup();
			for (ParticipationGroup participationGroup : participationGroups) {
				if (participationGroup.getName().equals(eui.getFolder().getName())) {
					participationGroup.addToOperationNavigation(result);
				}
			}			
		}		
		return result;
	}

//	@VisitBefore
//	public void visitParameter(INakedParameter parameter) {
//		if (parameter.getOwner() instanceof INakedOperation) {
//			INakedOperation owner = (INakedOperation) parameter.getOwner();
//			AbstractUserInteractionFolder entityFolder = findFolderFor(owner.getOwner());
//			if (owner.isQuery()) {
//				createParameter(parameter, entityFolder, OperationUserInteractionKind.QUERY);
//			} else if (!(owner.getOwner() instanceof INakedInterface)) {
//				createParameter(parameter, entityFolder, OperationUserInteractionKind.INVOKE);
//				createParameter(parameter, entityFolder, OperationUserInteractionKind.RETURN);
//				if (owner.isUserResponsibility()) {
//					createParameter(parameter, entityFolder, OperationUserInteractionKind.INBOX);
//				}
//			}
//		}
//	}

	private void createParameter(INakedParameter parameter, AbstractUserInteractionFolder entityFolder,
			OperationUserInteractionKind operationUserInteractionKind) {
		OperationUserInteraction oui = (OperationUserInteraction) entityFolder.findOwnedElement(getUserInteractionFQNameOf((INakedOperation) parameter
				.getOwner(), operationUserInteractionKind));
		ParameterParticipationKindMap kindMap = new ParameterParticipationKindMap(parameter, oui);
		TypedElementParticipationKind kind = kindMap.calculateParticipationKind();
		TypedElementParticipation pp = null;
		if (kind == TypedElementParticipationKind.NAVIGATION) {
			pp = createParameterNavigation(oui, parameter);
		} else {
			pp = createParameterField(oui, parameter);
		}
		Number displayIndex = getTag(parameter, participationIn(oui.getOperationUserInteractionKind()), PARTICIPATION_KIND);
		if (displayIndex == null) {
			displayIndex = getTag(parameter, PROPERTY_SPECIFICATION, DISPLAY_INDEX);
			if (displayIndex == null) {
				// TODO a bit more intelligence in calculating the index
				if (parameter.isReturn() || parameter.getDirection().equals(ParameterDirectionKind.OUT)) {
					displayIndex = parameter.getResultIndex();
				} else {
					displayIndex = parameter.getArgumentIndex();
				}
			}
		}
		pp.setDisplayIndex(displayIndex.doubleValue());
		pp.setParticipationKind(kind);
		DomainOperation operation = oui.getOperation();
		DomainParameter domainParameter = (DomainParameter) operation.findOwnedElement(getDomainNameOf(parameter));
		pp.setRepresentedElement(domainParameter);
		pp.setName(getDomainNameOf(parameter));
	}

	private ParameterNavigation createParameterNavigation(OperationUserInteraction oui, INakedParameter parameter) {
		ParameterNavigation result = new ParameterNavigation();
		oui.addToParameterNavigation(result);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(((IParameterOwner)parameter.getOwner()).getContext(), parameter);
		result.setResultingUserInteraction(findUserinteraction(parameter, map, (INakedInstanceSpecification) getTag(parameter, PARAMETER_SPECIFICATION,
				RESULTING_USER_INTERACTION)));
		return result;
	}

	private ParameterField createParameterField(OperationUserInteraction oui, INakedParameter parameter) {
		ParameterField result = new ParameterField();
		oui.addToParameterField(result);
		return result;
	}

	protected void populatePropertyParticipation(ClassifierUserInteraction eui, INakedProperty property, TypedElementParticipation participation,
			Number displayIndex) {
		participation.setName(getDomainNameOf(property));
		AbstractUserInteractionFolder entityFolder = eui.getFolder();
		if (entityFolder != null) {
			participation.setRepresentedElement(entityFolder.getRepresentedElement().findOwnedElement(getDomainNameOf(property)));
		}
		if (displayIndex == null) {
			displayIndex = getTag(property, PROPERTY_SPECIFICATION, DISPLAY_INDEX);
			if (displayIndex == null) {
				// TODO a bit more intelligence in calculating the index
				displayIndex = property.getOwnedAttributeIndex();
			}
			int weight = 1;
			INakedClassifier owner = property.getOwner();
			while (owner.getSupertype() != null) {
				weight *= 10;
				owner = owner.getSupertype();
			}
			displayIndex = displayIndex.doubleValue() * weight;
		}
		participation.setDisplayIndex(displayIndex.doubleValue());
	}

	private INakedInstanceSpecification findInstanceSpecificationFor(INakedElement root, INakedClassifier classifier) {
		Collection<? extends INakedElement> children = root.getOwnedElements();
		for (INakedElement nakedElement : children) {
			if (nakedElement instanceof INakedInstanceSpecification) {
				INakedInstanceSpecification is = (INakedInstanceSpecification) nakedElement;
				if (is.getClassifier().equals(classifier)) {
					return is;
				} else {
					is = findInstanceSpecificationFor(nakedElement, classifier);
					if (is != null) {
						return is;
					}
				}
			} else {
				INakedInstanceSpecification is = findInstanceSpecificationFor(nakedElement, classifier);
				if (is != null) {
					return is;
				}
			}
		}
		return null;
	}
}
