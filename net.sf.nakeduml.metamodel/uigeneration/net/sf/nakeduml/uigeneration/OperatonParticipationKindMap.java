package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_KIND;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.resolve;

import org.nakeduml.name.NameConverter;

import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class OperatonParticipationKindMap{
	private INakedOperation operation;
	private ClassifierUserInteraction eui;
	public OperatonParticipationKindMap(INakedOperation operation,ClassifierUserInteraction eui){
		super();
		this.operation = operation;
		this.eui = eui;
	}
	
	public static final <V extends Enum>String participationIn(V c){
		return "OperationParticipationIn" + NameConverter.capitalize(c.name().toLowerCase());
	}
	
	public OperationParticipationKind calculateParticipationKind(){
		OperationParticipationKind result = OperationParticipationKind.HIDDEN;
		INakedEnumerationLiteral tag = getTag(operation, participationIn(eui.getUserInteractionKind()), PARTICIPATION_KIND);
		if(tag == null){
			// initialize to default
			if(eui.getUserInteractionKind() == UserInteractionKind.EDIT){
				result = OperationParticipationKind.VISIBLE;
			}else if(eui.getUserInteractionKind() == UserInteractionKind.VIEW && operation.isQuery()){
				result = OperationParticipationKind.VISIBLE;
			}
		}else{
			result = resolve(tag, OperationParticipationKind.class);
		}
		if(result == OperationParticipationKind.VISIBLE){
			// Override
			if(eui.getUserInteractionKind() == UserInteractionKind.CREATE
					|| (!operation.isQuery() && eui.getUserInteractionKind() == UserInteractionKind.VIEW)){
				result = OperationParticipationKind.HIDDEN;
			}
		}
		return result;
	}
}
