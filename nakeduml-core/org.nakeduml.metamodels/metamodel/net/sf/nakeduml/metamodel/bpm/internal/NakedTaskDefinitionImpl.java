package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.Collection;

import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.bpm.TaskDelegation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedTaskDefinitionImpl extends NakedElementImpl implements INakedResponsibilityDefinition{
	private INakedValueSpecification potentialOwners;
	private INakedValueSpecification potentialBusinessAdministrators;
	private INakedValueSpecification potentialStakeHolders;
	private Collection<INakedDeadline> deadlines;
	private TaskDelegation delegation;
	@Override
	public INakedValueSpecification getPotentialOwners(){
		return this.potentialOwners;
	}
	@Override
	public INakedValueSpecification getPotentialBusinessAdministrators(){
		return this.potentialBusinessAdministrators;
	}
	@Override
	public INakedValueSpecification getPotentialStakeholders(){
		return this.potentialStakeHolders;
	}
	@Override
	public Collection<INakedDeadline> getDeadlines(){
		return this.deadlines;
	}
	@Override
	public String getMetaClass(){
		return "taskDefinition";
	}
	@Override
	public TaskDelegation getDelegation(){
		return this.delegation;
	}
	public INakedValueSpecification getPotentialStakeHolders(){
		return potentialStakeHolders;
	}
	public void setPotentialStakeHolders(INakedValueSpecification potentialStakeHolders){
		this.potentialStakeHolders = potentialStakeHolders;
	}
	public void setPotentialOwners(INakedValueSpecification potentialOwners){
		this.potentialOwners = potentialOwners;
	}
	public void setPotentialBusinessAdministrators(INakedValueSpecification potentialBusinessAdministrators){
		this.potentialBusinessAdministrators = potentialBusinessAdministrators;
	}
	public void setDeadlines(Collection<INakedDeadline> deadlines){
		this.deadlines = deadlines;
	}
	public void setDelegation(TaskDelegation delegation){
		this.delegation = delegation;
	}
}
