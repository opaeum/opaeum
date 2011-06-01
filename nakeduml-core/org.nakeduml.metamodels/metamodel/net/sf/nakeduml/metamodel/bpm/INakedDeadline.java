package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;

public interface INakedDeadline extends INakedTimeEvent{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
