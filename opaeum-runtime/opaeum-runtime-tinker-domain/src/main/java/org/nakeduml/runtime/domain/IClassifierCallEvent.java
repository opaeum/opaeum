package org.nakeduml.runtime.domain;

import org.nakeduml.runtime.domain.activity.AcceptCallAction;

public interface IClassifierCallEvent extends IClassifierEvent {
	AcceptCallAction getAcceptCallAction();
}
