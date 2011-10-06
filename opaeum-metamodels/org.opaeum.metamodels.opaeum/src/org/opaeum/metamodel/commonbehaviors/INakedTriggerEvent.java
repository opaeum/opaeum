package org.opeum.metamodel.commonbehaviors;


public interface INakedTriggerEvent extends INakedEvent{
	INakedTrigger getOwningTrigger();
	INakedBehavior getBehaviorContext();


}
