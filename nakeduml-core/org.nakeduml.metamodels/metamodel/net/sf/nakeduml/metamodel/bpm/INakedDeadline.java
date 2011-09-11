package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;

import org.nakeduml.runtime.domain.DeadlineKind;

public interface INakedDeadline extends INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
