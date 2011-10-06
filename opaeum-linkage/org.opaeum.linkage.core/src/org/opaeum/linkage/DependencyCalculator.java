package org.opaeum.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;

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
