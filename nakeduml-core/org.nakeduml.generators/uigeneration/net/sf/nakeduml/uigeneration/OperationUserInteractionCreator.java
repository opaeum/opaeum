package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.INBOX_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.INSTRUCTION_TO_USER;
import static net.sf.nakeduml.uigeneration.StereotypeNames.OPERATION_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SUCCESS_MESSAGE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.USER_INTERACTION_FOR_OWNER;
import static net.sf.nakeduml.uigeneration.StereotypeNames.USER_INTERACTION_FOR_RESULT;
import static net.sf.nakeduml.uigeneration.StereotypeNames.USER_ROLE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import net.sf.nakeduml.domainmetamodel.DomainOperation;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

@StepDependency(phase = UimTransformationPhase.class,requires = {CustomUserInteractionCreator.class},after = CustomUserInteractionCreator.class)
public class OperationUserInteractionCreator extends GeneratingUserInteractionTransformationStep{
	@VisitBefore
	public void visitOperation(INakedOperation o){
		AbstractUserInteractionFolder parentFolder = findFolderFor(o.getOwner());
		DomainOperation domainOperation = (DomainOperation) findDomainClassifierFor(o.getOwner()).findOwnedElement(getDomainNameOf(o));
		
		if (domainOperation==null) {
			System.out.println("Warning, domainOperation is null for " + o);
			return;
		}
		
		if(o.isQuery()){
			OperationUserInteraction userInteraction = buildUserInteraction(o, OPERATION_SPECIFICATION, domainOperation,
					OperationUserInteractionKind.QUERY);
			parentFolder.addToOperationUserInteraction(userInteraction);
		}else if (!(o.getOwner() instanceof INakedInterface)) {
			OperationUserInteraction userInteraction = buildUserInteraction(o, OPERATION_SPECIFICATION, domainOperation,
					OperationUserInteractionKind.INVOKE);
			parentFolder.addToOperationUserInteraction(userInteraction);
			parentFolder.addToOperationUserInteraction(buildUserInteraction(o, OPERATION_SPECIFICATION, domainOperation,
					OperationUserInteractionKind.RETURN));
		}
		if(o.getOwner() instanceof INakedInterface && o.getOwner().hasStereotype(USER_ROLE)){
			parentFolder.addToOperationUserInteraction(buildUserInteraction(o, INBOX_SPECIFICATION, domainOperation,
					OperationUserInteractionKind.INBOX));
		}
	}
	private OperationUserInteraction buildUserInteraction(INakedOperation o,String stereotypeName,DomainOperation domainOperation,
			OperationUserInteractionKind kind){
		// TODO make the user messages more clever
		OperationUserInteraction oui = new OperationUserInteraction();
		oui.setOperationUserInteractionKind(kind);
		oui.setName(getUserInteractionNameOf(o, kind));
		oui.setRepresentedElement(domainOperation);
		String successMessage = getTag(o, stereotypeName, SUCCESS_MESSAGE);
		if(successMessage != null){
			oui.setSuccessMessage(successMessage);
		}else{
			oui.setSuccessMessage("" + domainOperation.getHumanName() + " was successful");
		}
		String instruction = getTag(o, stereotypeName, INSTRUCTION_TO_USER);
		if(instruction != null){
			oui.setInstructionToUser(instruction);
		}else{
			oui.setInstructionToUser("Provide values to " + domainOperation.getHumanName());
		}
		oui.setUserInteractionForOwner(findUserInteractionForOwner(o, oui));
		oui.setUserInteractionForResult(findUserInteractionForResult(o, oui));
		return oui;
	}

	private ClassifierUserInteraction findUserInteractionForOwner(INakedOperation o,OperationUserInteraction oui){
		OperationUserInteractionKind ouiKind = oui.getOperationUserInteractionKind();
		boolean isTask = (ouiKind == OperationUserInteractionKind.INVOKE || ouiKind == OperationUserInteractionKind.RETURN)
				&& o.getOwner().hasStereotype(USER_ROLE) || ouiKind == OperationUserInteractionKind.INBOX;
		if(ouiKind == OperationUserInteractionKind.QUERY || isTask){
			return null;
		}else{
			// return or invoke
			INakedInstanceSpecification is = getTag(o, OPERATION_SPECIFICATION, USER_INTERACTION_FOR_OWNER);
			String uiName = getUserInteractionFQNameOf(o.getOwner(), UserInteractionKind.EDIT);
			if(is == null || ouiKind == OperationUserInteractionKind.RETURN){
				uiName = getUserInteractionFQNameOf(o.getOwner(), UserInteractionKind.VIEW);
			}else{
				uiName = getUserInteractionFQNameOf(is);
			}
			AbstractUserInteractionFolder result0 = findFolderFor(o.getOwner());
			ClassifierUserInteraction result = (ClassifierUserInteraction) result0.findOwnedElement(uiName);
			return result;
		}
	}
	private ClassifierUserInteraction findUserInteractionForResult(INakedOperation o,OperationUserInteraction oui){
		boolean hasResult = o.getReturnParameter() != null && o.getReturnParameter().getNakedBaseType() instanceof INakedComplexStructure;
		boolean resultsProduced = oui.getOperationUserInteractionKind() == OperationUserInteractionKind.QUERY
				|| oui.getOperationUserInteractionKind() == OperationUserInteractionKind.RETURN;
		if(!(hasResult && resultsProduced)){
			return null;
		}else{
			INakedClassifier baseType = o.getReturnParameter().getNakedBaseType();
			INakedInstanceSpecification is = getTag(o, OPERATION_SPECIFICATION, USER_INTERACTION_FOR_RESULT);
			String uiName = getUserInteractionNameOf(baseType, UserInteractionKind.LIST);
			if(is != null){
				uiName = getUserInteractionNameOf(is);
			}else if(o.getReturnParameter().getNakedMultiplicity().isSingleObject()){
				uiName = getUserInteractionNameOf(baseType, UserInteractionKind.VIEW);
			}
			return (ClassifierUserInteraction) findFolderFor(baseType).findOwnedElement(uiName);
		}
	}

}
