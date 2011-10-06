package org.opaeum.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedSlot;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opeum.runtime.domain.TaskDelegation;

public class NakedResponsibilityDefinitionImpl extends NakedElementImpl implements INakedResponsibilityDefinition{
	/**
	 * 
	 */
	private static final long serialVersionUID = -274701389815160284L;
	INakedInstanceSpecification stereotype;
	public NakedResponsibilityDefinitionImpl(INakedInstanceSpecification stereotype){
		this.stereotype = stereotype;
		this.id = stereotype.getId();
		super.mappingInfo = stereotype.getMappingInfo().getCopy();
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
	public Collection<INakedElement> getOwnedElements(){
		Set<INakedElement> result = new HashSet<INakedElement>();
		result.add(getPotentialBusinessAdministrators());
		result.add(getPotentialOwners());
		result.add(getPotentialStakeholders());
		result.addAll(getDeadlines());
		result.remove(null);
		return result;
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
		return v == null ? null : (TaskDelegation) v.getValue();
	}
	@Override
	public INakedClassifier getExpressionContext(){
		return getNearestClassifier();
	}
}
