package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.types.AttributeCallExp;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.expressions.internal.types.PropertyCallExp;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

@StepDependency(phase = LinkagePhase.class,after = {
	NakedParsedOclStringResolver.class
})
public class ClassDependencyCalculator extends AbstractModelElementLinker{
	@VisitBefore
	public void visitValueSpecification(INakedValueSpecification vs){
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
				walk((INakedClassifier) nameSpace, expression.getAppliedProperty());
			}
		}
	}
	private void walk(INakedClassifier nameSpace,PropertyCallExp appliedProperty){
		if(appliedProperty != null){
			if(appliedProperty instanceof AttributeCallExp){
				IAttribute referredAttribute = ((AttributeCallExp) appliedProperty).getReferredAttribute();
				IClassifier type = referredAttribute.getType();
				while(type.isCollectionKind()){
					type = ((StdlibCollectionType) type).getElementType();
				}
				if(type instanceof INakedClassifier){
					workspace.markDependency(nameSpace, (INakedClassifier) type);
				}
			}else if(appliedProperty instanceof OperationCallExp){
				OperationCallExp opExp = (OperationCallExp) appliedProperty;
				for(IOclExpression exp:opExp.getArguments()){
					walk(nameSpace, exp.getAppliedProperty());
				}
			}
			walk(nameSpace, appliedProperty.getAppliedProperty());
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
