package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedCallEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignalEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.expressionVisitors.AstWalker;
import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.oclengine.IOclContext;

@StepDependency(phase = LinkagePhase.class,after = {
	NakedParsedOclStringResolver.class
})
public class DependencyCalculator extends AbstractModelElementLinker{
	@VisitBefore
	public void visitValueSpecification(final INakedValueSpecification vs){
		if(vs.isValidOclValue()){
			IOclContext oclValue = vs.getOclValue();
			IOclExpression expression = oclValue.getExpression();
			INakedNameSpace nameSpace = vs.getNameSpace();
			while(!(nameSpace == null || nameSpace instanceof INakedClassifier)){
				nameSpace = nameSpace.getNameSpace();
			}
			if(nameSpace instanceof INakedBehavior && !BehaviorUtil.hasExecutionInstance((IParameterOwner) nameSpace)){
				nameSpace = ((INakedBehavior) nameSpace).getContext();
			}
			if(nameSpace instanceof INakedClassifier){
				AbstractOclVisitor abstractOclVisitor = new AbstractOclVisitor(){
					@Override
					public Object attributeCallExp(IAttributeCallExp exp){
						IModelElement referredAttribute = exp.getReferredAttribute();
						markDependency(vs, referredAttribute);
						return super.attributeCallExp(exp);
					}
					@Override
					public Object associationEndCallExp(IAssociationEndCallExp exp){
						IModelElement referredAttribute = exp.getReferredAssociationEnd();
						markDependency(vs, referredAttribute);
						return super.associationEndCallExp(exp);
					}
					@Override
					public Object associationClassCallExp(IAssociationClassCallExp exp){
						IModelElement referredAttribute = exp.getReferredAssociationClass();
						markDependency(vs, referredAttribute);
						return super.associationClassCallExp(exp);
					}
					@Override
					public Object enumLiteralExp(IEnumLiteralExp exp){
						IModelElement referredEnumLiteral = exp.getReferredEnumLiteral();
						markDependency(vs, referredEnumLiteral);
						IModelElement enumeration = exp.getReferredEnumLiteral().getEnumeration();
						markDependency(vs, enumeration);
						return super.enumLiteralExp(exp);
					}
					protected void markDependency(final INakedValueSpecification vs,IModelElement enumeration){
						if(enumeration instanceof INakedElement){
							workspace.markDependency(vs, (INakedElement) enumeration);
						}
					}
					@Override
					public Object oclStateLiteralExp(IOclStateLiteralExp exp){
						IModelElement referredState = exp.getReferredState();
						markDependency(vs, referredState);
						return super.oclStateLiteralExp(exp);
					}
					@Override
					public Object oclTypeLiteralExp(IOclTypeLiteralExp exp){
						IModelElement referredClassifier = exp.getReferredClassifier();
						markDependency(vs, referredClassifier);
						return super.oclTypeLiteralExp(exp);
					}
					@Override
					public Object operationCallExp(IOperationCallExp exp,List args){
						IModelElement referredOperation = exp.getReferredOperation();
						markDependency(vs, referredOperation);
						return super.operationCallExp(exp, args);
					}
					@Override
					public Object variableDeclaration(IVariableDeclaration exp,Object initExpression){
						IModelElement type = exp.getType();
						markDependency(vs, type);
						return super.variableDeclaration(exp, initExpression);
					}
				};
				new AstWalker().walk(expression, abstractOclVisitor);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation oper){
		INakedClassifier owner = oper.getOwner();
		markDependencyForParameters(oper, owner);
		if(owner instanceof INakedInterface){
			INakedInterface inf = (INakedInterface) owner;
			for(INakedClassifier nc:inf.getImplementingClassifiers()){
				markDependencyForParameters(oper, nc);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAction(INakedAction a){
		if(a instanceof INakedVariableAction){
			workspace.markDependency(a, ((INakedVariableAction) a).getVariable());
		}else if(a instanceof INakedCallAction){
			workspace.markDependency(a, ((INakedCallAction) a).getCalledElement());
		}else if(a instanceof INakedStructuralFeatureAction){
			workspace.markDependency(a, ((INakedStructuralFeatureAction) a).getFeature());
		}else if(a instanceof INakedSendSignalAction){
			workspace.markDependency(a, ((INakedSendSignalAction) a).getSignal());
		}else if(a instanceof INakedAcceptEventAction){
			for(INakedTrigger t:((INakedAcceptEventAction) a).getTriggers()){
				if(t.getEvent() instanceof INakedOperation){
					workspace.markDependency(a, ((INakedCallEvent) t.getEvent()).getOperation());
				}else if(t.getEvent() instanceof INakedSignalEvent){
					workspace.markDependency(a, ((INakedSignalEvent) t.getEvent()).getSignal());
				}
			}
		}
	}
	public void markDependencyForParameters(INakedOperation oper,INakedClassifier owner){
		for(INakedParameter p:oper.getOwnedParameters()){
			workspace.markDependency(owner, p.getNakedBaseType());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAttribute(INakedProperty p){
		workspace.markDependency(p.getOwner(), p.getNakedBaseType());
		if(p.getOwner() instanceof INakedInterface){
			INakedInterface inf = (INakedInterface) p.getOwner();
			// Safe to use implementing classifiers here (or is it?)
			for(INakedClassifier nc:inf.getImplementingClassifiers()){
				workspace.markDependency(nc, p.getNakedBaseType());
			}
		}
	}
	@VisitBefore
	public void visitGeneralization(INakedGeneralization g){
		workspace.markDependency(g.getSpecific(), g.getGeneral());
	}
	@VisitBefore
	public void visitInterfaceRealization(INakedInterfaceRealization g){
		workspace.markDependency(g.getImplementingClassifier(), g.getContract());
	}
}
