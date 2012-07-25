package org.opaeum.validation;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.internal.ParameterUtil;

@StepDependency(phase = ValidationPhase.class)
public class OperationValidation extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation o){
		if(o.isStatic() && o.getOwner() instanceof Interface){
			getErrorMap().putError(o, OperationValidationRule.INTERFACE_OPERATION_STATIC);
		}
		validateRedefinitionQueryStatus(o, o.getRedefinedOperations());
		validateRedefinitionQueryStatus(o, findOperationsRedefinedByName(o));
		if(!o.isQuery() && o.getBodyCondition()!=null && o.getBodyCondition().getSpecification()!=null){
			getErrorMap().putError(o, OperationValidationRule.BODY_CONDITIONS_REQUIRE_QUERY_OPERATION);

		}
	}

	protected Collection<Operation> findOperationsRedefinedByName(Operation o){
		Collection<Operation> byName = new HashSet<Operation>();
		String identifyingString = ParameterUtil.toIdentifyingString(o);
		for(Generalization g:((Classifier) o.getOwner()).getGeneralizations()){
			for(Operation eo:g.getGeneral().getEffectiveOperations()){
				if(identifyingString.equals(ParameterUtil.toIdentifyingString(eo))){
					byName.add(eo);
				}
			}
		}
		if(o.getOwner() instanceof BehavioredClassifier){
			for(InterfaceRealization ir:((BehavioredClassifier) o.getOwner()).getInterfaceRealizations()){
				for(Operation eo:ir.getContract().getEffectiveOperations()){
					if(identifyingString.equals(ParameterUtil.toIdentifyingString(eo))){
						byName.add(eo);
					}
				}
			}
		}
		return byName;
	}

	protected void validateRedefinitionQueryStatus(Operation o,Collection<Operation> redefinedOperations){
		for(Operation ro:redefinedOperations){
			if(o.isQuery() && !ro.isQuery()){
				getErrorMap().putError(o, OperationValidationRule.REDEFINED_OPERATION_IS_QUERY_CORRESPOND, ro);
			}else if(!o.isQuery() && ro.isQuery()){
				getErrorMap().putError(o, OperationValidationRule.REDEFINED_OPERATION_NOT_QUERY_CORRESPOND, ro);
			}
		}
	}
}
