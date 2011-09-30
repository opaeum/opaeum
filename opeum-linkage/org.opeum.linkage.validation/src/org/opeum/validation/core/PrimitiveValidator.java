package org.opeum.validation.core;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.linkage.CoreValidationRule;
import org.opeum.metamodel.core.INakedGeneralization;
import org.opeum.metamodel.core.INakedPrimitiveType;
import org.opeum.validation.AbstractValidator;
import org.opeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class PrimitiveValidator extends AbstractValidator{
	@VisitBefore
	public void visitPrimitive(INakedPrimitiveType p){
		if(p.getOclType() == null){
			getErrorMap().putError(p, CoreValidationRule.PRIMITIVE_TYPE_MUST_EXTEND_OCL_PRIMITIVE, "");
		}
		for(INakedGeneralization g:p.getNakedGeneralizations()){
			if(!(g.getGeneral() instanceof INakedPrimitiveType)){
				getErrorMap().putError(p, CoreValidationRule.PRIMITIVE_MUST_SPECIALIZE_PRIMITIVES, "");
			}
		}
		// TODO primitives and simple types may not have non derived attributes
	}
}
