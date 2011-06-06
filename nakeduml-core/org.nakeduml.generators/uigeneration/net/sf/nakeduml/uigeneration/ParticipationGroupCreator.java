package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_GROUP;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationInGroup;

import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.ParticipationGroup;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.nakeduml.name.NameConverter;

@StepDependency(phase = UserInteractionTransformationPhase.class, requires = { OperationUserInteractionCreator.class }, before = ParticipationCreator.class, after = OperationUserInteractionCreator.class)
public class ParticipationGroupCreator extends AbstractParticipationCreator {

	@VisitAfter
	public void visitEntities(INakedEntity c) {
		if (hasUserInteractions(c)) {
			for (INakedProperty p : c.getEffectiveAttributes()) {
				// if (!p.getNakedBaseType().getIsAbstract()) {
				INakedInstanceSpecification tag = (INakedInstanceSpecification) getTag(p, participationInGroup(UserInteractionKind.EDIT), PARTICIPATION_GROUP);
				if (tag != null) {
					AbstractUserInteractionFolder entityFolder = findFolderFor((INakedClassifier) c);
					Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
					for (ClassifierUserInteraction eui : entityUserInteractions) {
						if (eui.getUserInteractionKind() == UserInteractionKind.EDIT || eui.getUserInteractionKind() == UserInteractionKind.VIEW) {
							ParticipationGroup participationGroup = new ParticipationGroup();
							participationGroup.setName(tag.getName());
							participationGroup.setDisplayName(tag.getFirstValueFor("displayName").stringValue());
							participationGroup.setDisplayIndex(tag.getFirstValueFor("displayIndex").intValue());
							eui.addToParticipationGroup(participationGroup);
						}
					}
				} else {
					AbstractUserInteractionFolder entityFolder = findFolderFor((INakedClassifier) c);
					Set<ClassifierUserInteraction> entityUserInteractions = entityFolder.getEntityUserInteraction();
					for (ClassifierUserInteraction eui : entityUserInteractions) {
						if (eui.getUserInteractionKind() == UserInteractionKind.EDIT || eui.getUserInteractionKind() == UserInteractionKind.VIEW) {
							ParticipationGroup participationGroup = new ParticipationGroup();
							participationGroup.setName(entityFolder.getName());
							participationGroup.setDisplayName(NameConverter.decapitalize(entityFolder.getName()));
							participationGroup.setDisplayIndex(-1);
							eui.addToParticipationGroup(participationGroup);
						}
					}
				}
			}
		}
	}
	
	
}
