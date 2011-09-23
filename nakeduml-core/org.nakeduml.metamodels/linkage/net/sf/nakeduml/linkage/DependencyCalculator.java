package net.sf.nakeduml.linkage;

import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedCallEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignalEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;

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
