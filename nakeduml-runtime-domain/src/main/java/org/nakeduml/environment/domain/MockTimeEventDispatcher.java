package org.nakeduml.environment.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TimeUnit;

public class MockTimeEventDispatcher implements ITimeEventDispatcher {
	private class MockTimer {
		AbstractEntity process;
		String callBackMethodName;

		public AbstractEntity getProcess() {
			return process;
		}

		public String getCallBackMethodName() {
			return callBackMethodName;
		}

		public MockTimer(AbstractEntity process, String callBackMethodName) {
			super();
			this.process = process;
			this.callBackMethodName = callBackMethodName;
		}

		public int hashCode() {
			return (process.getClass().getName() + callBackMethodName).hashCode();
		}

		public boolean equals(Object other) {
			if (other instanceof MockTimer) {
				return process.equals(((MockTimer) other).process) && callBackMethodName.equals(((MockTimer) other).callBackMethodName);
			} else {
				return false;
			}
		}
	}

	private Map<MockTimer, Date> mockedEvents = new HashMap<MockTimer, Date>();

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date) {
		final MockTimer te = new MockTimer(process, callBackMethodNameParm);
		mockedEvents.put(te, date);
	}

	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit) {
		MockTimer te = new MockTimer(process, callBackMethodNameParm);
		// TODO apply WorkingHours
		Date date = new Date(System.currentTimeMillis() + calculateMiliseconds(duration, timeUnit));
		mockedEvents.put(te, date);
	}

	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm) {
		MockTimer te = new MockTimer(process, callBackMethodNameParm);
		mockedEvents.remove(te);
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

	private void dispatchEvent(MockTimer timeEvent) {
		AbstractEntity target = timeEvent.getProcess();
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

	public void fireMockedTimers(String methodName) {
		Collection<MockTimer> timers = mockedEvents.keySet();
		for (MockTimer timer : timers) {
			dispatchEvent(timer);
		}
	}

	@Override
	public Date getTimeScheduled(AbstractEntity process, String callBackMethodName) {
		MockTimer te = new MockTimer(process, callBackMethodName);
		return mockedEvents.get(te);
	}
}
