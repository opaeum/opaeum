package org.opeum.javageneration.jbpm5.statemachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opeum.metamodel.commonbehaviors.INakedStep;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.statemachines.INakedRegion;
import org.opeum.metamodel.statemachines.INakedState;
import org.opeum.metamodel.statemachines.INakedStateMachine;
import org.opeum.metamodel.statemachines.INakedTransition;

import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJEnum;

@StepDependency(phase = JavaTransformationPhase.class,requires = StateMachineImplementor.class,after = StateMachineImplementor.class)
public class StateEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@Override
	protected INakedStep getEnclosingElement(INakedElement s){
		INakedState state = (INakedState) s;
		if(state.hasEnclosingState()){
			return state.getEnclosingState();
		}else{
			return null;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedStateMachine c){
		boolean hasStateComposition = hasStateComposition(c);
		buildOJEnum(c, hasStateComposition);
		List<INakedRegion> regions = c.getRegions();
		regions(regions);
	}
	private void regions(List<INakedRegion> regions){
		for(INakedRegion r:regions){
			List<INakedState> states = r.getStates();
			for(INakedState s:states){
				state(s);
				regions(s.getRegions());
			}
		}
	}
	private boolean hasStateComposition(INakedStateMachine sm){
		for(INakedState s:sm.getAllStates()){
			if(s.hasEnclosingState()){
				return true;
			}
		}
		return false;
	}
	private void state(INakedState state){
		INakedStateMachine sm = state.getStateMachine();
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(sm.getParent()));
		OJEnum e = (OJEnum) p.findClass(new OJPathName(sm.getMappingInfo().getJavaName().getAsIs() + "State"));
		buildLiteral(state, e);
	}
	@Override
	protected Collection<INakedTrigger> getOperationTriggers(INakedElement step){
		INakedState state = (INakedState) step;
		Collection<INakedTrigger> result = new ArrayList<INakedTrigger>();
		List<INakedTransition> outgoing = state.getOutgoing();
		for(INakedTransition t:outgoing){
			Collection<INakedTrigger> triggers = t.getTriggers();
			for(INakedTrigger trigger:triggers){
				if(trigger.getEvent() instanceof INakedCallEvent){
					result.add(trigger);
				}
			}
		}
		return result;
	}
}
