package org.opaeum.javageneration.jbpm5.statemachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.statemachines.INakedRegion;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.INakedTransition;

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
		INakedStep enclosingElement = getEnclosingElement(state);
		buildLiteral(state, e, enclosingElement==null?"null":Jbpm5Util.stepLiteralName(enclosingElement));
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
