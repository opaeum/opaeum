package net.sf.nakeduml.jbpm;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;
import javax.inject.Inject;

import net.sf.nakeduml.seam3.Component;
import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.TimeUnit;

import org.hibernate.Session;
import org.jboss.seam.solder.beanManager.BeanManagerUnavailableException;


@Stateless
public class TimeEventDispatcher implements ITimeEventDispatcher {
	@Inject
	Session session;
	@Resource()
	TimerService timerService;
	Map<TimeEvent, Timer> mockedEvents=new HashMap<TimeEvent, Timer>();
	private static final TimeEventDispatcher mockInstance = new TimeEventDispatcher(new HashMap<TimeEvent, Timer>());

	public TimeEventDispatcher(HashMap<TimeEvent, Timer> hashMap) {
		mockedEvents = hashMap;
	}

	public TimeEventDispatcher() {
	}

	public TimerService getTimerService() {
		return timerService;
	}

//	public EntityManager getEntityManager() {
//		return (EntityManager) Component.INSTANCE.getInstance("entityManager");
//	}

	public static ITimeEventDispatcher getInstance() {
		try {
			ITimeEventDispatcher dispatcher = (ITimeEventDispatcher) Component.INSTANCE.getInstance(ITimeEventDispatcher.class);
			return dispatcher;
		} catch (BeanManagerUnavailableException e) {
			return mockInstance;
		}
	}

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date) {
		final TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		if (timerService != null) {
			createTimer(date, te);
		} else {
			mockTimer(date, te);
		}
	}

	private void mockTimer(final Date date, final TimeEvent te) {
		mockedEvents.put(te, new Timer() {
			@Override
			public long getTimeRemaining() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				return 0;
			}

			@Override
			public Date getNextTimeout() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				return date;
			}

			@Override
			public Serializable getInfo() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				return te;
			}

			@Override
			public TimerHandle getHandle() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				return null;
			}

			@Override
			public void cancel() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
			}

			@Override
			public ScheduleExpression getSchedule() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCalendarTimer() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isPersistent() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		// TODO apply WorkingHours
		Date date = new Date(System.currentTimeMillis() + calculateMiliseconds(duration, timeUnit));
		if (timerService != null) {
			createTimer(date, te);
		} else {
			mockTimer(date, te);
		}
	}

	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		if (timerService!=null) {
			Collection<Timer> timers = timerService.getTimers();
			for (Timer timer : timers) {
				if (timer.getInfo() instanceof TimeEvent) {
					TimeEvent currentTe = (TimeEvent) timer.getInfo();
					if (currentTe.equals(te)) {
						timer.cancel();
					}
				}
			}
		} else {
			mockedEvents.remove(te);
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

	public Timer getTimer(AbstractEntity process, String string) {
		return mockedEvents.get(new TimeEvent(process, string));
	}

	@Override
	public void fireMockedTimers(String methodName) {
		Collection<Timer> timers = mockedEvents.values();
		for (Timer timer : timers) {
			if (((TimeEvent) timer.getInfo()).getCallBackMethodName().equalsIgnoreCase(methodName)) {
				dispatch(timer);
			}
		}
	}
}
