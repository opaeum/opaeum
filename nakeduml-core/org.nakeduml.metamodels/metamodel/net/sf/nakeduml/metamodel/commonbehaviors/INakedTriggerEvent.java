package net.sf.nakeduml.metamodel.commonbehaviors;


public interface INakedTriggerEvent extends INakedEvent{
	INakedTrigger getOwningTrigger();
	INakedBehavior getBehaviorContext();


}
