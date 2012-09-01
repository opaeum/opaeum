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
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
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
				Classifier baseType = type instanceof CollectionType ? ((CollectionType) type).getElementType() : type;
				if(p.eContainingFeature().equals(UMLPackage.eINSTANCE.getConstraint_Specification())){
					Constraint c = (Constraint) p.eContainer();
					if(c.eContainer() instanceof Operation && ((Operation) c.eContainer()).getBodyCondition() == c){
						Operation o = (Operation) c.getContext();
						Type expectedType = o.getReturnResult().getType();
						if(o.getReturnResult() != null && expectedType != null)
							if(!EmfClassifierUtil.conformsTo(baseType, (Classifier) expectedType)){
								workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE,
										expectedType, baseType);
							}
					}else if(!EmfClassifierUtil.comformsToLibraryType(baseType, "Boolean")){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, getLibrary().getBooleanType(),
								baseType);
					}
				}else if(p.eContainer() instanceof TimeExpression && p.eContainer().eContainer() instanceof TimeEvent){
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
				}else if(StereotypesHelper.hasStereotype(p, StereotypeNames.PARTICIPANT_EXPRESSION)){
					if(!EmfClassifierUtil.conformsTo((Classifier) baseType, getLibrary().getParticipant())){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, getLibrary().getParticipant(),
								baseType);
					}
				}else if(StereotypesHelper.hasStereotype(p, StereotypeNames.RECIPIENT_EXPRESSION)){
					if(!EmfClassifierUtil.conformsTo((Classifier) baseType, getLibrary().getNotificationReceiver())){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE,
								getLibrary().getNotificationReceiver(), baseType);
					}
				}else if(StereotypesHelper.hasStereotype(p, StereotypeNames.BUSINESS_CALENDAR_EXPRESSION)){
					if(!EmfClassifierUtil.conformsTo((Classifier) baseType, getLibrary().getBusinessCalendar())){
						workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE,
								getLibrary().getBusinessCalendar(), baseType);
					}
				}else if(p.eContainingFeature().getName().equals("defaultValue")){
					Property prop = (Property) p.getOwner();
					if(prop.getType() != null){
						if(!EmfClassifierUtil.conformsTo(baseType, (Classifier) prop.getType())){
							workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, prop.getType(), baseType);
						}
					}
				}else if(p.eContainer().eContainingFeature().equals(UMLPackage.eINSTANCE.getOperation_BodyCondition())){
					Operation oper = (Operation) p.eContainer().eContainer();
					Parameter result = EmfBehaviorUtil.getReturnParameter(oper);
					if(result != null && result.getType() != null){
						if(!EmfClassifierUtil.conformsTo(baseType, (Classifier) result.getType())){
							workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, result.getType(), baseType);
						}
					}
				}else if(p.eContainingFeature().equals(UMLPackage.eINSTANCE.getValuePin_Value())){
					ValuePin vp = (ValuePin) p.eContainer();
					TypedElement linkedTypedElement = EmfActionUtil.getLinkedTypedElement(vp);
					if(linkedTypedElement != null && linkedTypedElement.getType() != null){
						if(!EmfClassifierUtil.conformsTo(baseType, (Classifier) linkedTypedElement.getType())){
							workspace.getErrorMap().putError(ctx.getBodyContainer(), CoreValidationRule.OCL_EXPECTED_TYPE, linkedTypedElement.getType(),
									baseType);
						}
					}
				}
			}
		}
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior p){
		if(!StereotypesHelper.hasStereotype(p, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK)){
			putErrors(p, getLibrary().getOclBehaviorContext(p));
		}
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
