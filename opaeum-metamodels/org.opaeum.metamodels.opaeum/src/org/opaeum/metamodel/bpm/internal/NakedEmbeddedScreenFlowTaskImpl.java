package org.opeum.metamodel.bpm.internal;

import org.opeum.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import org.opeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opeum.metamodel.core.INakedInstanceSpecification;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.core.internal.StereotypeNames;
import org.opeum.metamodel.statemachines.INakedStateMachine;

public class NakedEmbeddedScreenFlowTaskImpl extends NakedCallBehaviorActionImpl implements INakedEmbeddedScreenFlowTask{
	private static final long serialVersionUID = 2669838847662466125L;
	private INakedResponsibilityDefinition taskDefinition;
	private EmbeddedScreenFlowTaskMessageStructureImpl messageStructure;
	@Override
	public INakedResponsibilityDefinition getTaskDefinition(){
		return this.taskDefinition;
	}
	@Override
	public INakedStateMachine getScreenFlow(){
		return (INakedStateMachine) getBehavior();
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		if(stereotype.getName().equalsIgnoreCase(StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK)){
			taskDefinition = new NakedResponsibilityDefinitionImpl(stereotype);
			addOwnedElement(taskDefinition);
		}
		super.addStereotype(stereotype);
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return this.messageStructure;
	}
	@Override
	public void initMessageStructure(){
		this.messageStructure=new EmbeddedScreenFlowTaskMessageStructureImpl(this);
	}
}
