package org.opaeum.metamodel.bpm.internal;

import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class NakedResponsibilityImpl extends NakedOperationImpl implements INakedResponsibility{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3576293633166379156L;
	private INakedResponsibilityDefinition taskDefinition;
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	public INakedResponsibilityDefinition getTaskDefinition(){
		return this.taskDefinition;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.getName().equalsIgnoreCase(StereotypeNames.RESPONSIBILITY)){
			this.taskDefinition=new NakedResponsibilityDefinitionImpl(stereotype);
			addOwnedElement(taskDefinition);

		}
	}
}
