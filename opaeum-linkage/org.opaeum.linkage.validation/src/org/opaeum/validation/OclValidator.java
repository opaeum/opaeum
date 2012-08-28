package org.opaeum.validation;

import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

@StepDependency(phase = ValidationPhase.class,after = {},requires = {},before = {})
public class OclValidator extends AbstractValidator{
	@VisitBefore
	public void visitOpaqueExpression(OpaqueExpression p){
		if(!EmfElementUtil.isMarkedForDeletion(p)){
			OpaqueExpressionContext ctx = getLibrary().getOclExpressionContext(p);
			putErrors(p, ctx);
			if(!ctx.hasErrors()){
				Classifier type = ctx.getExpression().getType();
				Classifier baseType=type instanceof CollectionType?((CollectionType) type).getElementType():type;
				if(p.eContainer() instanceof TimeExpression && p.eContainer().eContainer() instanceof TimeEvent){
					TimeEvent te = (TimeEvent) p.eContainer().eContainer();
					ValueSpecification expr = te.getWhen().getExpr();
					if(te.isRelative()
							&& !(EmfClassifierUtil.comformsToLibraryType(type, "Integer") || EmfClassifierUtil.comformsToLibraryType(type, "Real"))){
						workspace.getErrorMap().putError(expr, CoreValidationRule.OCL_EXPECTED_TYPE, getLibrary().getIntegerType(), type);
					}else if(!te.isRelative()
							&& !(EmfClassifierUtil.conformsTo(type, getLibrary().getDateTimeType()) || EmfClassifierUtil.conformsTo(type, getLibrary()
									.getDateType()))){
						workspace.getErrorMap().putError(expr, CoreValidationRule.OCL_EXPECTED_TYPE, getLibrary().getDateTimeType(), type);
					}
				}else if( StereotypesHelper.hasStereotype(p, StereotypeNames.PARTICIPANT_EXPRESSION)){
					if(!EmfClassifierUtil.conformsTo((Classifier) type, getLibrary().getParticipant())){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, getLibrary().getParticipant(),
								baseType);
					}
				}else if( StereotypesHelper.hasStereotype(p, StereotypeNames.RECIPIENT_EXPRESSION)){
					if(!EmfClassifierUtil.conformsTo((Classifier) type, getLibrary().getNotificationReceiver())){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE,
								getLibrary().getNotificationReceiver(), type);
					}
				}else if(p.eContainingFeature().getName().equals("defaultValue")){
					Property prop = (Property) p.getOwner();
					if(prop.getType() != null){
						Classifier elementType = type;
						if(type instanceof CollectionType){
							elementType = ((CollectionType) type).getElementType();
						}
						if(!EmfClassifierUtil.conformsTo(elementType, (Classifier) prop.getType())){
							workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, prop.getType(), type);
						}
					}
				}
			}
		}
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
			OCLHelper<Classifier,Operation,Property,Constraint> helper = oclActionContext.getHelper();
			workspace.getErrorMap().putError(p, CoreValidationRule.OCL, helper.getProblems());
		}
	}
	@VisitAfter
	public void visitResponsibility(Operation o){
	}
}
