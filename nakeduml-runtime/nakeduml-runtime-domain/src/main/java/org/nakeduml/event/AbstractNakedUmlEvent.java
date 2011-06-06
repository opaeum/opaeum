package org.nakeduml.event;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.drools.runtime.process.ProcessContext;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.nakeduml.annotation.PersistentName;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IntrospectionUtil;

@Entity()
@Table(name = "numl_abstract_event")
public abstract class AbstractNakedUmlEvent implements Retryable{
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
	@Basic
	@Transient
	private boolean toBeCancelled;
	@Transient
	private int retryCount;
	// For Mocking purposes
	@Transient
	private IPersistentObject eventSource;
	@Basic
	private String nodeInstanceId;
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
	public AbstractNakedUmlEvent(IPersistentObject target,String callBackMethodName,ProcessContext ctx){
		this(target, callBackMethodName);
		this.nodeInstanceId = ((NodeInstanceImpl) ctx.getNodeInstance()).getUniqueId();
		this.eventSourceClassName = IntrospectionUtil.getOriginalClass(target.getClass()).getAnnotation(PersistentName.class).value();
	}
	public AbstractNakedUmlEvent(IPersistentObject process,String callBackMethodName2,boolean cancelled){
		this(process, callBackMethodName2);
		this.toBeCancelled = cancelled;
	}
	private AbstractNakedUmlEvent(IPersistentObject process,String callBackMethodName){
		this.eventSource = process;
		this.eventSourceId = process.getId();
		this.callbackMethodName = callBackMethodName;
	}
	public Long getEventSourceId(){
		return eventSourceId;
	}
	public String getNodeInstanceId(){
		return nodeInstanceId;
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
	public Class<? extends IPersistentObject> getEventSourceClass(){
		try{
			return Environment.getPersistentNameClassMap().getClass(eventSourceClassName);
		}catch(Exception e){
			throw new ExceptionAnalyser(e).wrapRootCauseIfNecessary();
		}
	}
	// For Mocking Purposes
	public IPersistentObject getEventSource(){
		return eventSource;
	}
	protected Method getMethodByPersistentName(String persistentMethodName,Type...parameterTypes){
		Method[] methods = getEventSourceClass().getMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(PersistentName.class) && method.getAnnotation(PersistentName.class).value().equals(persistentMethodName)
					&& method.getParameterTypes().length == parameterTypes.length){
				for(int i = 0;i < parameterTypes.length;i++){
					if(parameterTypes[i] != method.getParameterTypes()[i]){
						break;
					}
				}
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
	public abstract void invokeCallback(IPersistentObject eventSource2);
}
