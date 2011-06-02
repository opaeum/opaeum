package net.sf.nakeduml.metamodel.commonbehaviors;


public interface INakedContextualEvent extends INakedEvent{
	INakedBehavior getBehaviorContext();
	INakedTrigger getOwningTrigger();

}
