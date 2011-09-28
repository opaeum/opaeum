package net.sf.nakeduml.validation;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.internal.ParameterUtil;

@StepDependency(phase = ValidationPhase.class)
public class OperationValidation extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(o.isStatic() && o.getOwner() instanceof INakedInterface){
			getErrorMap().putError(o, OperationValidationRule.INTERFACE_OPERATION_STATIC);
		}
		validateRedefinitionQueryStatus(o, o.getRedefinedOperations());
		validateRedefinitionQueryStatus(o, findOperationsRedefinedByName(o));
		if(!o.isQuery() && o.getBodyCondition()!=null && o.getBodyCondition().getSpecification()!=null){
			getErrorMap().putError(o, OperationValidationRule.BODY_CONDITIONS_REQUIRE_QUERY_OPERATION);

		}
	}

	protected Collection<INakedOperation> findOperationsRedefinedByName(INakedOperation o){
		Collection<INakedOperation> byName = new HashSet<INakedOperation>();
		String identifyingString = ParameterUtil.toIdentifyingString(o);
		for(INakedGeneralization g:o.getOwner().getNakedGeneralizations()){
			for(INakedOperation eo:g.getGeneral().getEffectiveOperations()){
				if(identifyingString.equals(ParameterUtil.toIdentifyingString(eo))){
					byName.add(eo);
				}
			}
		}
		if(o.getOwner() instanceof INakedBehavioredClassifier){
			for(INakedInterfaceRealization ir:((INakedBehavioredClassifier) o.getOwner()).getInterfaceRealizations()){
				for(INakedOperation eo:ir.getContract().getEffectiveOperations()){
					if(identifyingString.equals(ParameterUtil.toIdentifyingString(eo))){
						byName.add(eo);
					}
				}
			}
		}
		return byName;
	}

	protected void validateRedefinitionQueryStatus(INakedOperation o,Collection<INakedOperation> redefinedOperations){
		for(INakedOperation ro:redefinedOperations){
			if(o.isQuery() && !ro.isQuery()){
				getErrorMap().putError(o, OperationValidationRule.REDEFINED_OPERATION_IS_QUERY_CORRESPOND, ro);
			}else if(!o.isQuery() && ro.isQuery()){
				getErrorMap().putError(o, OperationValidationRule.REDEFINED_OPERATION_NOT_QUERY_CORRESPOND, ro);
			}
		}
	}
}
