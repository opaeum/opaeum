package org.opeum.metamodel.bpm;

import org.opeum.metamodel.commonbehaviors.INakedTimer;

import org.opeum.runtime.domain.DeadlineKind;

public interface INakedDeadline extends INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
