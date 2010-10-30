package net.sf.nakeduml.seam;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractProcess;
import net.sf.nakeduml.util.TimeUnit;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.async.Asynchronous;
import org.jboss.seam.async.AsynchronousInvocation;
import org.jboss.seam.contexts.Contexts;

@Scope(ScopeType.EVENT)
@Name("timeEventDispatcher")
public class TimeEventDispatcher {
	private static Method method;
	static {
		try {
			TimeEventDispatcher.class.getMethod("dispatchEvent", TimeEvent.class);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	};
	@Resource
	TimerService timerService;
	@In
	EntityManager entityManager;
	Map<TimeEvent, Timer> mockedEvents;
	private static final TimeEventDispatcher mockInstance = new TimeEventDispatcher(new HashMap<TimeEvent, Timer>());

	public TimeEventDispatcher(HashMap<TimeEvent, Timer> hashMap) {
		mockedEvents = hashMap;
	}

	public TimeEventDispatcher() {
	}

	public static TimeEventDispatcher getInstance() {
		if (Contexts.isEventContextActive()) {
			TimeEventDispatcher dispatcher = (TimeEventDispatcher) Component.getInstance("timeEventDispatcher");
			return dispatcher;
		} else {
			return mockInstance;
		}
	}

	public void scheduleEvent(AbstractProcess process, String callBackMethodNameParm, final Date date) {
		final TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		if (Contexts.isEventContextActive()) {
			createTimer(date, te);
		} else {
			mockTimer(date, te);
		}
	}

	public void mockTimer(final Date date, final TimeEvent te) {
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
		});
	}

	public void scheduleEvent(AbstractProcess process, String callBackMethodNameParm, long duration, TimeUnit timeUnit) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		// TODO apply WorkingHours
		Date date = new Date(System.currentTimeMillis() + calculateMiliseconds(duration, timeUnit));
		if (Contexts.isEventContextActive()) {
			createTimer(date, te);
		} else {
			mockTimer(date, te);
		}
	}

	public void cancelTimer(AbstractProcess process, String callBackMethodNameParm) {
		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
		if (Contexts.isEventContextActive()) {
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
			duration *= 60000;
		}
		return duration;
	}

	private void createTimer(Date date, TimeEvent te) {
		timerService.createTimer(date, new AsynchronousInvocation(method, "timeEventDispatcher", new Object[] { te }));
	}

	@Timeout
	public void dispatch(Timer timer) {
		// Setup context and invoke
		((Asynchronous) timer.getInfo()).execute(timer);
	}

	public void dispatchEvent(TimeEvent timeEvent) {
		AbstractProcess target = entityManager.find(timeEvent.processClass, timeEvent.processId);
		if (target != null) {
			try {
				target.getClass().getMethod(timeEvent.callBackMethodName).invoke(target);
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

	public static class TimeEvent implements Serializable {
		private static final long serialVersionUID = 1859791139038182369L;
		Long processId;
		Class<? extends AbstractProcess> processClass;
		String callBackMethodName;

		public TimeEvent(AbstractProcess process, String callBackMethodName) {
			super();
			this.processId = ((AbstractEntity) process).getId();
			this.processClass = process.getClass();
			this.callBackMethodName = callBackMethodName;
		}

		public int hashCode() {
			return processClass.hashCode();
		}

		public boolean equals(Object other) {
			if (other instanceof TimeEvent) {
				TimeEvent te = (TimeEvent) other;
				return te.processClass.equals(processClass) && te.processId.equals(processId)
						&& callBackMethodName.equals(te.callBackMethodName);
			} else {
				return false;
			}
		}
	}

	public Timer getTimer(AbstractProcess process, String string) {
		return mockedEvents.get(new TimeEvent(process, string));
	}
}
