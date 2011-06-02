package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.bpm.TaskDelegation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedResponsibilityDefinitionImpl extends NakedElementImpl implements INakedResponsibilityDefinition{
	INakedInstanceSpecification stereotype;
	public NakedResponsibilityDefinitionImpl(INakedInstanceSpecification stereotype){
		this.stereotype=stereotype;
		
	}
	@Override
	public INakedValueSpecification getPotentialOwners(){
		return (INakedValueSpecification) stereotype.getFirstValueFor("potentialOwners").getValue();
	}
	@Override
	public INakedValueSpecification getPotentialBusinessAdministrators(){
		return (INakedValueSpecification) stereotype.getFirstValueFor("potentialBusinessAdministrators").getValue();
	}
	@Override
	public INakedValueSpecification getPotentialStakeholders(){
		return (INakedValueSpecification) stereotype.getFirstValueFor("potentialStakeholders").getValue();
	}
	@Override
	public Collection<INakedDeadline> getDeadlines(){
		Collection<INakedDeadline> result = new ArrayList<INakedDeadline>();
		INakedSlot slot = stereotype.getSlotForFeature("deadlines");
		List<INakedValueSpecification> values = slot.getValues();
		for(INakedValueSpecification v:values){
			result.add((INakedDeadline) v.getValue());
		}
		return result;
	}
	@Override
	public String getMetaClass(){
		return "taskDefinition";
	}
	@Override
	public TaskDelegation getDelegation(){
		return (TaskDelegation) stereotype.getFirstValueFor("delegation").getValue();
	}
	@Override
	public INakedClassifier getExpressionContext(){
		return getNearestClassifier();
	}
}
