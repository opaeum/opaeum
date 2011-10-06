package org.opeum.validation;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.core.INakedGeneralization;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedInterfaceRealization;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.internal.ParameterUtil;

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
