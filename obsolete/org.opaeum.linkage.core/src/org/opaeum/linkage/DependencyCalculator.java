package org.opaeum.linkage;

import org.eclipse.uml2.uml.INakedAcceptEventAction;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedCallAction;
import org.eclipse.uml2.uml.INakedCallEvent;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedGeneralization;
import org.eclipse.uml2.uml.INakedInterface;
import org.eclipse.uml2.uml.INakedInterfaceRealization;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedParameter;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedSendSignalAction;
import org.eclipse.uml2.uml.INakedSignalEvent;
import org.eclipse.uml2.uml.INakedStructuralFeatureAction;
import org.eclipse.uml2.uml.INakedTrigger;
import org.eclipse.uml2.uml.INakedVariableAction;
import org.eclipse.uml2.uml.ITargetElement;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;

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
			ITargetElement target = ((INakedSendSignalAction) a).getTargetElement();
			if(target!=null && target.getNakedBaseType() !=null){
				workspace.markDependency(target.getNakedBaseType(), ((INakedSendSignalAction) a).getSignal());
			}
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
