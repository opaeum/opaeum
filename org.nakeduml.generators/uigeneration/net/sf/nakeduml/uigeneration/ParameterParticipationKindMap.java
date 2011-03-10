package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.PARTICIPATION_KIND;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import static net.sf.nakeduml.uigeneration.StereotypeNames.participationIn;
import static net.sf.nakeduml.uigeneration.StereotypeNames.resolve;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;
import nl.klasse.octopus.model.ParameterDirectionKind;

public class ParameterParticipationKindMap{
	private INakedParameter parameter;
	private OperationUserInteraction operationUserinteraction;
	public ParameterParticipationKindMap(INakedParameter parameter,OperationUserInteraction operationUserinteraction){
		super();
		this.parameter = parameter;
		this.operationUserinteraction = operationUserinteraction;
	}
	public TypedElementParticipationKind calculateParticipationKind(){
		INakedEnumerationLiteral literal = getTag(parameter, participationIn(operationUserinteraction.getOperationUserInteractionKind()), PARTICIPATION_KIND);
		TypedElementParticipationKind result = TypedElementParticipationKind.READONLY;
		if(literal == null){
			result = calculateDefault();
		}else{
			result = resolve(literal, TypedElementParticipationKind.class);
		}
		result = override(result);
		return result;
	}
	private TypedElementParticipationKind override(TypedElementParticipationKind result){
		// Override incorrect settings
		if(operationUserinteraction.getOperationUserInteractionKind() == OperationUserInteractionKind.INBOX){
			if(parameter.getDirection().equals(ParameterDirectionKind.IN)
					&& (result == TypedElementParticipationKind.READWRITE || result == TypedElementParticipationKind.REQUIRED)){
				result = TypedElementParticipationKind.READONLY;
			}
		}else{
			if((parameter.getDirection().equals(ParameterDirectionKind.OUT) || isReturn())
					&& (result == TypedElementParticipationKind.READWRITE || result == TypedElementParticipationKind.REQUIRED)){
				result = TypedElementParticipationKind.READONLY;
			}
		}
		return result;
	}
	private TypedElementParticipationKind calculateDefault(){
		TypedElementParticipationKind result=TypedElementParticipationKind.READONLY;
		// initialize sensible defaults
		if(operationUserinteraction.getOperationUserInteractionKind() == OperationUserInteractionKind.INBOX){
			if(isOut() || isReturn()){
				if(parameter.getNakedMultiplicity().getLower() == 1){
					result = TypedElementParticipationKind.REQUIRED;
				}else{
					result = TypedElementParticipationKind.READWRITE;
				}
			}else if(parameter.getNakedMultiplicity().isSingleObject()){
				result = TypedElementParticipationKind.READONLY;
			}else{
				result = TypedElementParticipationKind.NAVIGATION;
			}
		}else{
			if(isIn()){
				if(parameter.getNakedMultiplicity().getLower() == 1){
					result = TypedElementParticipationKind.REQUIRED;
				}else{
					result = TypedElementParticipationKind.READWRITE;
				}
			}else if(isReturn() || parameter.getNakedMultiplicity().isSingleObject()){
				result = TypedElementParticipationKind.READONLY;
			}else{
				result = TypedElementParticipationKind.NAVIGATION;
			}
		}
		return result;
	}
	private boolean isReturn(){
		return parameter.isReturn();
	}
	private boolean isIn(){
		return parameter.getDirection().equals(ParameterDirectionKind.IN) || parameter.getDirection().equals(ParameterDirectionKind.INOUT);
	}
	private boolean isOut(){
		return parameter.getDirection().equals(ParameterDirectionKind.OUT) || parameter.getDirection().equals(ParameterDirectionKind.INOUT);
	}
}
