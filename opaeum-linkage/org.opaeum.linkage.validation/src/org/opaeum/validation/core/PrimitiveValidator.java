package org.opaeum.validation.core;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

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
