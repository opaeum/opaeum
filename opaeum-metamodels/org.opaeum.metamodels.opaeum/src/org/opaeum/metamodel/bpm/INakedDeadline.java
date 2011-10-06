package org.opaeum.metamodel.bpm;

import org.opaeum.metamodel.commonbehaviors.INakedTimer;
import org.opeum.runtime.domain.DeadlineKind;

public interface INakedDeadline extends INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
