package net.sf.nakeduml.seam;


//TODO Weld
//@Scope(ScopeType.EVENT)
//@Name("timeEventDispatcher")
//@Stateless()
public class TimeEventDispatcher /*implements ITimeEventDispatcher*/ {
//	@Resource()
//	TimerService timerService;
//	Map<TimeEvent, Timer> mockedEvents=new HashMap<TimeEvent, Timer>();
//	private static final TimeEventDispatcher mockInstance = new TimeEventDispatcher(new HashMap<TimeEvent, Timer>());
//
//	public TimeEventDispatcher(HashMap<TimeEvent, Timer> hashMap) {
//		mockedEvents = hashMap;
//	}
//
//	public TimeEventDispatcher() {
//	}
//
//	public TimerService getTimerService() {
//		return timerService;
//	}
//
//	public EntityManager getEntityManager() {
//		return (EntityManager) Component.getInstance("entityManager");
//	}
//
//	public static ITimeEventDispatcher getInstance() {
//		if (Contexts.isEventContextActive()) {
//			ITimeEventDispatcher dispatcher = (ITimeEventDispatcher) Component.getInstance("timeEventDispatcher");
//			return dispatcher;
//		} else {
//			return mockInstance;
//		}
//	}
//
//	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, final Date date) {
//		final TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
//		if (timerService != null) {
//			createTimer(date, te);
//		} else {
//			mockTimer(date, te);
//		}
//	}
//
//	private void mockTimer(final Date date, final TimeEvent te) {
//		mockedEvents.put(te, new Timer() {
//			@Override
//			public long getTimeRemaining() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
//				return 0;
//			}
//
//			@Override
//			public Date getNextTimeout() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
//				return date;
//			}
//
//			@Override
//			public Serializable getInfo() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
//				return te;
//			}
//
//			@Override
//			public TimerHandle getHandle() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
//				return null;
//			}
//
//			@Override
//			public void cancel() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
//			}
//		});
//	}
//
//	public void scheduleEvent(AbstractEntity process, String callBackMethodNameParm, long duration, TimeUnit timeUnit) {
//		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
//		// TODO apply WorkingHours
//		Date date = new Date(System.currentTimeMillis() + calculateMiliseconds(duration, timeUnit));
//		if (timerService != null) {
//			createTimer(date, te);
//		} else {
//			mockTimer(date, te);
//		}
//	}
//
//	public void cancelTimer(AbstractEntity process, String callBackMethodNameParm) {
//		TimeEvent te = new TimeEvent(process, callBackMethodNameParm);
//		if (timerService!=null) {
//			Collection<Timer> timers = timerService.getTimers();
//			for (Timer timer : timers) {
//				if (timer.getInfo() instanceof TimeEvent) {
//					TimeEvent currentTe = (TimeEvent) timer.getInfo();
//					if (currentTe.equals(te)) {
//						timer.cancel();
//					}
//				}
//			}
//		} else {
//			mockedEvents.remove(te);
//		}
//	}
//
//	private static long calculateMiliseconds(long duration, TimeUnit timeUnit) {
//		// TODO replace with calls to WorkingHours
//		switch (timeUnit) {
//		case CALENDAR_WEEK:
//			duration *= 7;
//		case CALENDAR_DAY:
//			duration *= 24;
//		case ACTUAL_HOUR:
//			duration *= 60;
//		case ACTUAL_MINUTE:
//			duration *= 60;
//		case ACTUAL_SECOND:
//			duration *= 1000;
//		}
//		return duration;
//	}
//
//	private void createTimer(Date date, TimeEvent te) {
//		timerService.createTimer(date, te);
//	}
//
//	@Timeout
//	public void dispatch(Timer timer) {
//		dispatchEvent((TimeEvent) timer.getInfo());
//	}
//
//	private void dispatchEvent(TimeEvent timeEvent) {
//		AbstractEntity target = getEntityManager().find(timeEvent.getProcessClass(), timeEvent.getProcessId());
//		if (target != null) {
//			try {
//				target.getClass().getMethod(timeEvent.getCallBackMethodName()).invoke(target);
//			} catch (SecurityException e) {
//				throw new RuntimeException(e);
//			} catch (IllegalAccessException e) {
//				throw new RuntimeException(e);
//			} catch (InvocationTargetException e) {
//				throw new RuntimeException(e);
//			} catch (NoSuchMethodException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}
//
//	public Timer getTimer(AbstractEntity process, String string) {
//		return mockedEvents.get(new TimeEvent(process, string));
//	}
//
//	@Override
//	public void fireMockedTimers(String methodName) {
//		Collection<Timer> timers = mockedEvents.values();
//		for (Timer timer : timers) {
//			if (((TimeEvent) timer.getInfo()).getCallBackMethodName().equalsIgnoreCase(methodName)) {
//				dispatch(timer);
//			}
//		}
//	}
}
