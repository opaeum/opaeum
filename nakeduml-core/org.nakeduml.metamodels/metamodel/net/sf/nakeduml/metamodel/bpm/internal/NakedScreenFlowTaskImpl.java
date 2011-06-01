package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.bpm.INakedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class NakedScreenFlowTaskImpl extends NakedCallBehaviorActionImpl implements INakedScreenFlowTask{
	private INakedResponsibilityDefinition taskDefinition;
	@Override
	public INakedResponsibilityDefinition getTaskDefinition(){
		return this.taskDefinition;
	}
	@Override
	public void setTaskDefinition(INakedResponsibilityDefinition taskDefinition){
		this.taskDefinition=taskDefinition;
	}
	@Override
	public INakedStateMachine getScreenFlow(){
		return (INakedStateMachine) getBehavior();
	}
}
