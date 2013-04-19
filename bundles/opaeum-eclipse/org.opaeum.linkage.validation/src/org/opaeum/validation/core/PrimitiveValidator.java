package org.opaeum.validation.core;

import org.eclipse.uml2.uml.PrimitiveType;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class PrimitiveValidator extends AbstractValidator{
	@VisitBefore
	public void visitPrimitive(PrimitiveType p){
		if(!(EmfClassifierUtil.comformsToLibraryType(p, "Real") || EmfClassifierUtil.comformsToLibraryType(p, "String")
				|| EmfClassifierUtil.comformsToLibraryType(p, "Integer") || EmfClassifierUtil.comformsToLibraryType(p, "Boolean") || EmfClassifierUtil
					.comformsToLibraryType(p, "UnlimitedNatural"))){
			getErrorMap().putError(p, CoreValidationRule.PRIMITIVE_TYPE_MUST_EXTEND_OCL_PRIMITIVE, "");
		}
		// TODO primitives and simple types may not have non derived attributes
	}
}
