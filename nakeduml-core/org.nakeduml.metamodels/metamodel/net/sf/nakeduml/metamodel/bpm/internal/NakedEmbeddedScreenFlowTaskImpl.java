package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class NakedEmbeddedScreenFlowTaskImpl extends NakedCallBehaviorActionImpl implements INakedEmbeddedScreenFlowTask{
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
