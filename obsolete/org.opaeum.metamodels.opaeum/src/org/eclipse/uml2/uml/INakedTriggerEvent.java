package org.eclipse.uml2.uml;


public interface INakedTriggerEvent extends INakedEvent{
	INakedTrigger getOwningTrigger();
	INakedBehavior getBehaviorContext();


}
