package net.sf.nakeduml.validation.core;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.CoreValidationRule;
import net.sf.nakeduml.validation.ValidationPhase;

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
