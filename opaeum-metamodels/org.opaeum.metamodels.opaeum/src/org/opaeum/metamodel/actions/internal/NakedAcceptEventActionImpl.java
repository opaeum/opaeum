package org.opaeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedTypedElement;

public class NakedAcceptEventActionImpl extends NakedActionImpl implements INakedAcceptEventAction{
	private static final long serialVersionUID = -4255852720379805141L;
	private List<INakedOutputPin> result;
	private Collection<INakedTrigger> triggers=new HashSet<INakedTrigger>();
	public Set<INakedInputPin> getInput(){
		return new HashSet<INakedInputPin>();
	}
	public boolean isLongRunning(){
		return true;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedTrigger){
			triggers.add((INakedTrigger) element);
		}
	}
	@Override
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedTrigger){
			triggers.remove(element);
		}
		return result;
	}
	public Collection<INakedTrigger> getTriggers(){
		return triggers;
	}
	public List<INakedOutputPin> getResult(){
		return this.result;
	}
	public Collection<INakedOutputPin> getOutput(){
		return new ArrayList<INakedOutputPin>(this.getResult());
	}
	public void setResult(List<INakedOutputPin> result){
		replacePins(this.result,result);
		this.result = result;
	}
	@Override
	public boolean requiresEventRequest(){
		return containsTriggerType(INakedTimeEvent.class) || containsTriggerType(INakedChangeEvent.class);
	}
	@Override
	public List<? extends INakedTypedElement> getParameters(){
		return NakedTriggerImpl.getParameters(getTriggers());
	}
	@Override
	public boolean containsTriggerType(Class<? extends INakedEvent> t){
		for(INakedTrigger tr:getTriggers()){
			if(t.isInstance(tr.getEvent())){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean triggeredByTimeEventsOnly(){
		for(INakedTrigger tr:getTriggers()){
			if(!(tr.getEvent() instanceof INakedTimeEvent)){
				return false;
			}
		}
		return true;
	}
}
