package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

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
	public void removeOwnedElement(INakedElement element){
		super.removeOwnedElement(element);
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
