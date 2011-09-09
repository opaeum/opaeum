package net.sf.nakeduml.metamodel.bpm;

import org.nakeduml.runtime.domain.DeadlineKind;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;

public interface INakedDeadline extends INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
