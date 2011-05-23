package org.nakeduml.event;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.TimeUnit;

@Entity
@Table(name = "numl_time_event")
public class TimeEvent extends AbstractNakedUmlEvent{
	private static final long serialVersionUID = 1859791139038182369L;
	@Temporal(TemporalType.TIMESTAMP)
	Date when;
	public TimeEvent(){
	}
	public TimeEvent(AbstractEntity process,String callBackMethodName, boolean cancelled){
		super(process, callBackMethodName,cancelled);
		
	}
	public TimeEvent(AbstractEntity process,String callBackMethodName,Date when){
		super(process, callBackMethodName);
		this.when = when;
	}
	public TimeEvent(AbstractEntity process,String callBackMethodName,int duration,TimeUnit timeUnit){
		super(process, callBackMethodName);
		this.when = new Date(System.currentTimeMillis()+calculateMiliseconds(duration, timeUnit));
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