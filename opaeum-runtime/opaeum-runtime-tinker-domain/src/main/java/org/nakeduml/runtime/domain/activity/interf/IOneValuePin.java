package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.SingleObjectToken;

public interface IOneValuePin<O> extends IValuePin<O, SingleObjectToken<O>> {
	O getValue();
}
