package org.opeum.linkage;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.actions.INakedCallAction;
import org.opeum.metamodel.actions.INakedSendSignalAction;
import org.opeum.metamodel.actions.INakedStructuralFeatureAction;
import org.opeum.metamodel.actions.INakedVariableAction;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedGeneralization;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedInterfaceRealization;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedParameter;
import org.opeum.metamodel.core.INakedProperty;

@StepDependency(phase = LinkagePhase.class,after = {
	
})
public class DependencyCalculator extends AbstractModelElementLinker{
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
				if(t.getEvent() instanceof INakedCallEvent){
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
		for(INakedProperty sp:p.getSubsettedProperties()){
			workspace.markDependency(p, sp);
		}
		for(INakedProperty rp:p.getRedefinedProperties()){
			workspace.markDependency(p, rp);
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
