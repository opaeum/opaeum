package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;

public class NakedResponsibilityImpl extends NakedOperationImpl implements INakedResponsibility{
	@Override
	public boolean isLongRunning(){
		return true;
	}
	private INakedResponsibilityDefinition taskDefinition;
	@Override
	public INakedResponsibilityDefinition getTaskDefinition(){
		return this.taskDefinition;
	}
	@Override
	public void setTaskDefinition(INakedResponsibilityDefinition taskDefinition){
		this.taskDefinition=taskDefinition;
	}
}
