package org.nakeduml.event;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.drools.runtime.process.ProcessContext;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.TimeUnit;

@Entity
@Table(name = "numl_time_event")
public class TimeEvent extends AbstractNakedUmlEvent{
	private static final long serialVersionUID = 1859791139038182369L;
	@Temporal(TemporalType.TIMESTAMP)
	Date when;
	public TimeEvent(){
	}
	public TimeEvent(IPersistentObject target,String callBackMethodName, boolean cancelled){
		super(target, callBackMethodName,cancelled);
		
	}
	public TimeEvent(IPersistentObject target,String callBackMethodName,Date when,ProcessContext ctx){
		super(target, callBackMethodName,ctx);
		this.when = when;
	}
	public TimeEvent(IPersistentObject target,String callBackMethodName,int duration,TimeUnit timeUnit,ProcessContext ctx){
		super(target, callBackMethodName,ctx);
		this.when = new Date(System.currentTimeMillis()+calculateMiliseconds(duration, timeUnit));
	}
	public void invokeCallback(IPersistentObject context){
		try{
			getMethodByPersistentName(getCallbackMethodName(),String.class,Date.class).invoke(context, getNodeInstanceId(),when);
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}

	private static long calculateMiliseconds(long duration,TimeUnit timeUnit){
		// TODO replace with calls to WorkingHours
		switch(timeUnit){
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

}