package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.INSTRUCTION_TO_USER;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SUCCESS_MESSAGE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.USER_INTERACTION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.USER_INTERACTION_KIND;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.resolve;

import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

@StepDependency(phase = UserInteractionTransformationPhase.class,requires = {UserInteractionCreator.class},after = UserInteractionCreator.class)
public class CustomUserInteractionCreator extends GeneratingUserInteractionTransformationStep{
	@VisitBefore
	public void visitGeneralization(INakedGeneralization g){
		AbstractUserInteractionFolder general = findFolderFor(g.getGeneral());
		if (general!=null) {
			AbstractUserInteractionFolder specific = findFolderFor(g.getSpecific());

			//specific is null when the classifier is abstract
			if (specific!=null) {
				Set<ClassifierUserInteraction> uis = general.getEntityUserInteraction();
				for(ClassifierUserInteraction ui:uis){
					if(!ui.isCustom()){
						String uiName = getUserInteractionFQNameOf(g.getSpecific(), ui.getUserInteractionKind());
						ClassifierUserInteraction  spec = (ClassifierUserInteraction) specific.findOwnedElement(uiName);
						spec.setSuperClassifierUserInteraction(ui);
					}
				}
			}
			
		}
	}
	@VisitBefore()
	public void visitInstance(INakedInstanceSpecification instance){
		if(hasUserInteractions(instance.getClassifier())){
			// TODO make the user messages more clever
			//TODO should we populate the rest of the fields from the default ui of this kind?
			ClassifierUserInteraction eui = new ClassifierUserInteraction();
			eui.setName(getUserInteractionNameOf(instance));
			eui.setCustom(true);
			DomainClassifier dc = findDomainClassifierFor(instance.getClassifier());
			eui.setRepresentedElement(dc);
			eui.setFolder(findFolderFor(instance.getClassifier()));
			INakedEnumerationLiteral kind = getTag(instance, USER_INTERACTION, USER_INTERACTION_KIND);
			if(kind == null){
				eui.setUserInteractionKind(UserInteractionKind.EDIT);
			}else{
				eui.setUserInteractionKind(resolve(kind, UserInteractionKind.class));
			}
			String successMessage = getTag(instance, USER_INTERACTION, SUCCESS_MESSAGE);
			if(successMessage != null){
				eui.setSuccessMessage(successMessage);
			}else{
				eui.setSuccessMessage("" + instance.getMappingInfo().getHumanName() + " was successful");
			}
			String instruction = getTag(instance, USER_INTERACTION, INSTRUCTION_TO_USER);
			if(instruction != null){
				eui.setInstructionToUser(instruction);
			}else{
				eui.setInstructionToUser("Provide values to " + instance.getMappingInfo().getHumanName());
			}
		}
	}
}
