package org.nakeduml.event;

import java.lang.reflect.Method;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.nakeduml.annotation.PersistentName;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IntrospectionUtil;

@Entity()
@Table(name = "numl_abstract_event")
public class AbstractNakedUmlEvent implements Retryable{
	private static final long serialVersionUID = 8920092390485701533L;
	@Id
	Long id;
	@Basic
	@Column(name = "event_source_id")
	private Long eventSourceId;
	@Basic
	@Column(name = "event_source_class_name")
	private String eventSourceClassName;
	@Basic
	@Column(name = "callback_method_name")
	private String callbackMethodName;
	@Transient
	private boolean toBeCancelled;
	@Transient
	private int retryCount;
	// For Mocking purposes
	@Transient
	private AbstractEntity eventSource;
	@Override
	public String getDescription(){
		return getEventSourceClassName() + "." + getCallbackMethodName() + "()";
	}
	@Override
	public int getRetryCount(){
		return retryCount;
	}
	public AbstractNakedUmlEvent(){
	}
	public AbstractNakedUmlEvent(AbstractEntity process,String callBackMethodName){
		this.eventSource = process;
		this.eventSourceId = process.getId();
		this.eventSourceClassName = IntrospectionUtil.getOriginalClass(process.getClass()).getAnnotation(PersistentName.class).value();
		this.callbackMethodName = callBackMethodName;
	}
	public AbstractNakedUmlEvent(AbstractEntity process,String callBackMethodName2,boolean cancelled){
		this(process,callBackMethodName2);
		this.toBeCancelled=cancelled;
	}
	public Long getEventSourceId(){
		return eventSourceId;
	}
	public void invokeCallback(AbstractEntity context){
		try{
			getCallbackMethod().invoke(context);
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}
	public int hashCode(){
		return getEventSourceClass().hashCode();
	}
	public boolean equals(Object other){
		if(other instanceof AbstractNakedUmlEvent){
			AbstractNakedUmlEvent te = (AbstractNakedUmlEvent) other;
			return te.getEventSourceClass().equals(getEventSourceClass()) && te.getEventSourceId().equals(getEventSourceId())
					&& getCallbackMethodName().equals(te.getCallbackMethodName());
		}else{
			return false;
		}
	}
	public String getEventSourceClassName(){
		return eventSourceClassName;
	}
	public Class<? extends AbstractEntity> getEventSourceClass(){
		try{
			return Environment.getPersistentNameClassMap().getClass(eventSourceClassName);
		}catch(Exception e){
			throw new ExceptionAnalyser(e).wrapRootCauseIfNecessary();
		}
	}
	// For Mocking Purposes
	public AbstractEntity getEventSource(){
		return eventSource;
	}
	private Method getCallbackMethod(){
		return getMethodByPersistentName(callbackMethodName);
	}
	protected Method getMethodByPersistentName(String persistentMethodName){
		Method[] methods = getEventSourceClass().getMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(PersistentName.class) && method.getAnnotation(PersistentName.class).value().equals(persistentMethodName)){
				return method;
			}
		}
		return null;// Error condition
	}
	public String getCallbackMethodName(){
		return callbackMethodName;
	}
	public boolean isToBeCancelled(){
		return toBeCancelled;
	}
	public void cancel(){
		toBeCancelled = true;
	}
	@Override
	public void incrementRetryCount(){
		retryCount++;
	}
}
