package org.nakeduml.environment;

import java.util.Date;

import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TimeUnit;

public interface ITimeEventDispatcher {
	public void fireMockedTimers(String methodName);

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date);

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit);

	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm);

	public Date getTimeScheduled(AbstractEntity process, String onAbsoluteTimeEvent);
}
