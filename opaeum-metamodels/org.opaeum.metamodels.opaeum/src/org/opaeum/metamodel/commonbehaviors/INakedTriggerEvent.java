package org.opaeum.metamodel.commonbehaviors;


public interface INakedTriggerEvent extends INakedEvent{
	INakedTrigger getOwningTrigger();
	INakedBehavior getBehaviorContext();


}
