package org.nakeduml.environment;

import java.util.Date;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.TimeUnit;

public interface ITimeEventDispatcher {
	public void fireMockedTimers(String methodName);

	public void scheduleEvent(IPersistentObject process, String callBackMethodNameParm, final Date date);

	public void scheduleEvent(IPersistentObject process, String callBackMethodNameParm, long duration, TimeUnit timeUnit);

	public void cancelTimer(IPersistentObject process, String callBackMethodNameParm);

	public Date getTimeScheduled(IPersistentObject process, String onAbsoluteTimeEvent);
}
