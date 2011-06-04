package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.stdlib.IOclLibrary;

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
	public INakedMessageStructure getMessageStructure(IOclLibrary lib){
		if(messageStructure==null){
			this.messageStructure=new EmbeddedScreenFlowTaskMessageStructureImpl(this, lib);
		}
		return this.messageStructure;
	}
}
