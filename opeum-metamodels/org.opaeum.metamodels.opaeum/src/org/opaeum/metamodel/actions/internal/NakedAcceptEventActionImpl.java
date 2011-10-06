package org.opeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.internal.NakedActionImpl;
import org.opeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opeum.metamodel.commonbehaviors.INakedEvent;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedTypedElement;

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
	public void removeOwnedElement(INakedElement element, boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedTrigger){
			triggers.remove(element);
		}
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
		removePins(this.result);
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
