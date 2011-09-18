package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

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
