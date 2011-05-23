package org.nakeduml.environment.adaptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.hibernate.Session;
import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.event.TimeEvent;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TimeUnit;

@Stateless
public class TimeEventDispatcher implements ITimeEventDispatcher{
	@Inject
	Session session;
	@Resource()
	TimerService timerService;
	public TimeEventDispatcher(){
	}
	public TimerService getTimerService(){
		return timerService;
	}
	public void scheduleEvent(AbstractEntity process,String callBackMethodNameParm,final Date date){
	}
	public void scheduleEvent(AbstractEntity process,String callBackMethodNameParm,long duration,TimeUnit timeUnit){
	}
	public void cancelTimer(AbstractEntity process,String callBackMethodNameParm){
	}
	@Timeout
	public void dispatch(Timer timer){
	}
	private void dispatchEvent(TimeEvent timeEvent){
	}
	@Override
	public void fireMockedTimers(String methodName){
	}
	@Override
	public Date getTimeScheduled(AbstractEntity process,String callBackMethodNameParm){
		return null;
	}
}
