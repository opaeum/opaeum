package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedTriggerImpl extends NakedElementImpl implements INakedTrigger{
	private INakedElement event;
	private boolean isHumanTrigger;
	@Override
	public String getMetaClass(){
		return "trigger";
	}
	public void setEvent(INakedElement event){
		if(event instanceof INakedEvent && !(event instanceof INakedDeadline)){
			removeOwnedElement(this.event);
			addOwnedElement(event);
			event.setOwnerElement(this);
		}
		this.event = event;
	}
	public INakedElement getEvent(){
		return event;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		if(element instanceof INakedEvent){
			this.event = element;
		}
		super.addOwnedElement(element);
	}
	@Override
	public void addStereotype(INakedInstanceSpecification s){
		super.addStereotype(s);
		if(s.hasValueForFeature("isHumanTrigger")){
			isHumanTrigger = s.getFirstValueFor("isHumanTrigger").booleanValue();
		}
	}
	public boolean isHumanTrigger(){
		return isHumanTrigger;
	}
}
