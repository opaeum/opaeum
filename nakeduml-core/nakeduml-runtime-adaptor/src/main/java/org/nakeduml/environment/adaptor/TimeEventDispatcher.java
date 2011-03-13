package org.nakeduml.environment.adaptor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.hibernate.Session;
import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TimeUnit;

@Stateless
public class TimeEventDispatcher implements ITimeEventDispatcher {
	@Inject
	Session session;
	@Resource()
	TimerService timerService;

	public TimeEventDispatcher() {
	}

	public TimerService getTimerService() {
		return timerService;
	}

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date) {
		final TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		createTimer(date, te);
	}

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		// TODO apply WorkingHours
		Date date = new Date(System.currentTimeMillis() + calculateMiliseconds(duration, timeUnit));
		createTimer(date, te);
	}

	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			if (timer.getInfo() instanceof TimeEvent) {
				TimeEvent currentTe = (TimeEvent) timer.getInfo();
				if (currentTe.equals(te)) {
					timer.cancel();
				}
			}
		}
	}

	private static long calculateMiliseconds(long duration, TimeUnit timeUnit) {
		// TODO replace with calls to WorkingHours
		switch (timeUnit) {
		case CALENDAR_WEEK:
			duration *= 7;
		case CALENDAR_DAY:
			duration *= 24;
		case ACTUAL_HOUR:
			duration *= 60;
		case ACTUAL_MINUTE:
			duration *= 60;
		case ACTUAL_SECOND:
			duration *= 1000;
		}
		return duration;
	}

	private void createTimer(Date date, TimeEvent te) {
		timerService.createTimer(date, te);
	}

	@Timeout
	public void dispatch(Timer timer) {
		dispatchEvent((TimeEvent) timer.getInfo());
	}

	private void dispatchEvent(TimeEvent timeEvent) {
		AbstractEntity target = (AbstractEntity) session.get(timeEvent.getProcessClass(), timeEvent.getProcessId());
		if (target != null) {
			try {
				target.getClass().getMethod(timeEvent.getCallBackMethodName()).invoke(target);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void fireMockedTimers(String methodName) {
	}

	@Override
	public Date getTimeScheduled(AbstractEntity process, String callBackMethodNameParm) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			if (timer.getInfo() instanceof TimeEvent) {
				TimeEvent currentTe = (TimeEvent) timer.getInfo();
				if (currentTe.equals(te)) {
					return timer.getNextTimeout();
				}
			}
		}
		return null;
	}
}
