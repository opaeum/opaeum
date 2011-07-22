package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.TaskDelegation;

import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedResponsibilityDefinitionImpl extends NakedElementImpl implements INakedResponsibilityDefinition{
	INakedInstanceSpecification stereotype;
	public NakedResponsibilityDefinitionImpl(INakedInstanceSpecification stereotype){
		this.stereotype = stereotype;
		this.id = stereotype.getId();
	}
	@Override
	public INakedValueSpecification getPotentialOwners(){
		INakedValueSpecification v = stereotype.getFirstValueFor("potentialOwners");
		return v == null ? null : (INakedValueSpecification) v.getValue();
	}
	@Override
	public INakedValueSpecification getPotentialBusinessAdministrators(){
		INakedValueSpecification v = stereotype.getFirstValueFor("potentialBusinessAdministrators");
		return v == null ? null : (INakedValueSpecification) v.getValue();
	}
	@Override
	public INakedValueSpecification getPotentialStakeholders(){
		INakedValueSpecification v = stereotype.getFirstValueFor("potentialStakeholders");
		return v == null ? null : (INakedValueSpecification) v.getValue();
	}
	@Override
	public Collection<INakedDeadline> getDeadlines(){
		Collection<INakedDeadline> result = new ArrayList<INakedDeadline>();
		INakedSlot slot = stereotype.getSlotForFeature("deadlines");
		if(slot != null){
			List<INakedValueSpecification> values = slot.getValues();
			for(INakedValueSpecification v:values){
				result.add((INakedDeadline) v.getValue());
			}
		}
		return result;
	}
	@Override
	public String getMetaClass(){
		return "taskDefinition";
	}
	@Override
	public TaskDelegation getDelegation(){
		INakedValueSpecification v = stereotype.getFirstValueFor("delegation");
		return v==null?null:(TaskDelegation) v.getValue();
	}
	@Override
	public INakedClassifier getExpressionContext(){
		return getNearestClassifier();
	}
}
