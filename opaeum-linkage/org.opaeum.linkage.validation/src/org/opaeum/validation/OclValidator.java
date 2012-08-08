package org.opaeum.validation;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.ocl.uml.AbstractOclContext;

@StepDependency(phase = ValidationPhase.class,after = {},requires = {},before = {})
public class OclValidator extends AbstractValidator{
	@VisitBefore
	public void visitOpaqueExpression(OpaqueExpression p){
		putErrors(p, getLibrary().getOclExpressionContext(p));
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior p){
		putErrors(p, getLibrary().getOclBehaviorContext(p));
	}
	@VisitBefore
	public void visitOpaqueAction(OpaqueAction p){
		putErrors(p, getLibrary().getOclActionContext(p));
	}
	protected void putErrors(Element p,AbstractOclContext oclActionContext){
		if(oclActionContext.hasErrors()){
			workspace.getErrorMap().putError(p, CoreValidationRule.OCL);
		}
	}
}
