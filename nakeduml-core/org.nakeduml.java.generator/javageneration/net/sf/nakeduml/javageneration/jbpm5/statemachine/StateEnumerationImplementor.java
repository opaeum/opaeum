package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;

import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJEnum;

@StepDependency(phase = JavaTransformationPhase.class,requires = StateMachineImplementor.class,after = StateMachineImplementor.class)
public class StateEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@Override
	protected INakedElement getEnclosingElement(INakedElement s){
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
		for(INakedState s:c.getAllStates()){
			state(s);
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
	protected Collection<INakedTrigger> getMethodTriggers(INakedElement step){
		INakedState state = (INakedState) step;
		Collection<INakedTrigger> result = new ArrayList<INakedTrigger>();
		List<INakedTransition> outgoing = state.getOutgoing();
		for(INakedTransition t:outgoing){
			if(t.getTrigger() != null && t.getTrigger().getEvent() instanceof INakedOperation){
				result.add(t.getTrigger());
			}
		}
		return result;
	}
}
