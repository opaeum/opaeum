package net.sf.nakeduml.seam;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.TimeUnit;
@Local
public interface ITimeEventDispatcher {
	public TimerService getTimerService();

	public EntityManager getEntityManager();
	
	public void fireMockedTimers(String methodName);

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date);

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit);

	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm);
}
