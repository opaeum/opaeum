package net.sf.nakeduml.uigeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;

@StepDependency(phase = UimTransformationPhase.class,requires = ParticipationCreator.class,after = ParticipationCreator.class)
public class SlotParticipationCreator extends AbstractParticipationCreator{
	@VisitBefore
	public void visitInstance(INakedInstanceSpecification is){
//		if(hasUserInteractions(is.getClassifier())){
//			List<? extends INakedProperty> attrs = is.getClassifier().getEffectiveAttributes();
//			ClassifierUserInteraction userInteraction = findUserInteraction(is);
//			for(INakedProperty a:attrs){
//				if(is.getSlotForFeature(a.getName()) == null){
//					ClassifierUserInteraction cui = (ClassifierUserInteraction) findFolderFor(a.getOwner()).findOwnedElement(
//							getUserInteractionNameOf(is.getClassifier(), userInteraction.getUserInteractionKind()));
//					TypedElementParticipation pp = (TypedElementParticipation) cui.findOwnedElement(getDomainNameOf(a)).makeCopy();
//					if(pp instanceof PropertyField){
//						userInteraction.addToOwnedPropertyField((PropertyField) pp);
//					}else{
//						userInteraction.addToOwnedPropertyNavigation((PropertyNavigation) pp);
//					}
//				}
//			}
//		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSlot(INakedSlot slot){
//		INakedInstanceSpecification instance = slot.getOwningInstance();
//		if(hasUserInteractions(instance.getClassifier())){
//			// because it could also be a stereotype application
//			ClassifierUserInteraction userInteraction = findUserInteraction(instance);
//			PropertyParticipationKindMap kindMap = new PropertyParticipationKindMap(slot, userInteraction);
//			TypedElementParticipation participation = null;
//			TypedElementParticipationKind kind = kindMap.calculateParticipationKind();
//			if(kind == TypedElementParticipationKind.NAVIGATION){
//				PropertyNavigation result = new PropertyNavigation();
//				userInteraction.addToOwnedPropertyNavigation(result);
//				INakedInstanceSpecification r = getTag(slot, PARTICIPATION, RESULTING_USER_INTERACTION);
//				NakedStructuralFeatureMap map=OJUtil.buildStructuralFeatureMap(slot.getDefiningFeature());
//				result.setResultingUserInteraction(findUserinteraction(slot.getDefiningFeature(),map, r));
//				participation = result;
//			}else{
//				PropertyField result = new PropertyField();
//				userInteraction.addToOwnedPropertyField(result);
//				result.setAdditionalSecurityOnView(createSecureUserAction(slot, SECURITY_ON_EDIT));
//				participation = result;
//			}
//			participation.setAdditionalSecurityOnView(createSecureUserAction(slot, SECURITY_ON_VIEW));
//			String featureDomainName = getDomainNameOf(slot.getDefiningFeature());
//			participation.setName(featureDomainName);
//			participation.setRepresentedElement(findDomainClassifierFor(instance.getClassifier()).findOwnedElement(featureDomainName));
//			participation.setParticipationKind(kind);
//		}
	}
	private ClassifierUserInteraction findUserInteraction(INakedInstanceSpecification instance){
		AbstractUserInteractionFolder classFolder = findFolderFor(instance.getClassifier());
		ClassifierUserInteraction userInteraction = (ClassifierUserInteraction) classFolder.findOwnedElement(
				getUserInteractionFQNameOf(instance));
		return userInteraction;
	}
}
