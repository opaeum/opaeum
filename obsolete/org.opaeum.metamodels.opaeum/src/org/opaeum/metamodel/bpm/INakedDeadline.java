package org.opaeum.metamodel.bpm;

import org.eclipse.uml2.uml.INakedTimer;
import org.opaeum.runtime.domain.DeadlineKind;

public interface INakedDeadline extends INakedTimer{
	DeadlineKind getKind();
	INakedDefinedResponsibility getOrigin();
}
