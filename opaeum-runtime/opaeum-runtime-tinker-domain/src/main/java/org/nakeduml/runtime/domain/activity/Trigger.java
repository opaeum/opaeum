package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;
import org.nakeduml.runtime.domain.activity.interf.IEvent;
import org.nakeduml.runtime.domain.activity.interf.ITrigger;

public abstract class Trigger extends BaseTinkerSoftDelete implements ITrigger {

	private static final long serialVersionUID = 5709531503304555463L;

	@Override
	public boolean accepts(IEvent event) {
		return event.getClass().isAssignableFrom(getEventClass());
	}

}
