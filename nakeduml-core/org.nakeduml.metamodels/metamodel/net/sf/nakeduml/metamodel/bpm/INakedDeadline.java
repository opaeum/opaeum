package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;

public interface INakedDeadline extends INakedEvent,INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
