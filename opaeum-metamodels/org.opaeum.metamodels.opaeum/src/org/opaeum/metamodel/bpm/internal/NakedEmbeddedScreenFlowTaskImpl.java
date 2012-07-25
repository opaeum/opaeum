package org.opaeum.metamodel.bpm.internal;

import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedMessageStructure;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.opaeum.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class NakedEmbeddedScreenFlowTaskImpl extends NakedCallBehaviorActionImpl implements INakedEmbeddedScreenFlowTask{
	private static final long serialVersionUID = 2669838847662466125L;
	private INakedResponsibilityDefinition taskDefinition;
	private EmbeddedScreenFlowTaskMessageStructure messageStructure;
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
		this.messageStructure=new EmbeddedScreenFlowTaskMessageStructure(this);
	}
}
