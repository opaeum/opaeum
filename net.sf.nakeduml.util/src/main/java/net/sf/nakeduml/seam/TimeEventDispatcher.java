package net.sf.nakeduml.seam;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractProcess;

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
	static final List<TimeEvent> mockedEvents = new ArrayList<TimeEventDispatcher.TimeEvent>();
	public static void scheduleEvent(Date date, final AbstractProcess process, final String callBackMethodNameParm) {
		TimeEvent te = new TimeEvent(((AbstractEntity) process).getId(), process.getClass(), callBackMethodNameParm);
		if (Contexts.isEventContextActive()) {
			TimeEventDispatcher dispatcher = (TimeEventDispatcher) Component.getInstance("timeEventDispatcher");
			dispatcher.createTimer(date, dispatcher, te);
		}else{
			mockedEvents.add(te);
		}
	}

	public void createTimer(Date date, TimeEventDispatcher dispatcher, TimeEvent te) {
		timerService.createTimer(date, new AsynchronousInvocation(method, "timeEventDispatcher", new Object[] { te }));
	}

	@Timeout
	public void dispatch(Timer timer) {
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

	public static class TimeEvent {
		Long processId;
		Class<? extends AbstractProcess> processClass;
		String callBackMethodName;

		public TimeEvent(Long processId, Class<? extends AbstractProcess> processClass, String callBackMethodName) {
			super();
			this.processId = processId;
			this.processClass = processClass;
			this.callBackMethodName = callBackMethodName;
		}
	}
}
