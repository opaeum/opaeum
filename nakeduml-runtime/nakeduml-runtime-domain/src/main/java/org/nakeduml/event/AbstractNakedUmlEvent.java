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
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IntrospectionUtil;

@Entity()
@Table(name = "numl_abstract_event")
public abstract class AbstractNakedUmlEvent implements AsynchronouslyDelivered{
	private static final long serialVersionUID = 8920092390485701533L;
	@Id
	Long id;
	@Basic
	@Column(name = "event_source_id")
	private Long eventSourceId;
	@Basic
	@Column(name = "event_source_class_id")
	private Integer eventSourceClassId;
	@Basic
	@Column(name = "callback_method_uuid")
	private String callbackMethodUuid;
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
		return getEventSourceClass().getName() + ".event";
	}
	@Override
	public int getRetryCount(){
		return retryCount;
	}
	public AbstractNakedUmlEvent(){
	}
	public AbstractNakedUmlEvent(IPersistentObject target,String callBackMethodUuid,ProcessContext ctx){
		this(target, callBackMethodUuid);
		this.nodeInstanceId = ((NodeInstanceImpl) ctx.getNodeInstance()).getUniqueId();
		this.eventSourceClassId = Environment.getMetaInfoMap().getNakedUmlId(IntrospectionUtil.getOriginalClass(target.getClass()));
	}
	public AbstractNakedUmlEvent(IPersistentObject process,String callBackMethodName2,boolean cancelled, ProcessContext ctx){
		this(process, callBackMethodName2,ctx);
		this.toBeCancelled = cancelled;
	}
	private AbstractNakedUmlEvent(IPersistentObject process,String callBackMethodUuid){
		this.eventSource = process;
		this.eventSourceId = process.getId();
		this.eventSourceClassId = Environment.getMetaInfoMap().getNakedUmlId(IntrospectionUtil.getOriginalClass(process.getClass()));
		this.callbackMethodUuid = callBackMethodUuid;
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
					&& getCallbackMethodUuid().equals(te.getCallbackMethodUuid());
		}else{
			return false;
		}
	}
	public Class<?> getEventSourceClass(){
		return Environment.getMetaInfoMap().getClass(eventSourceClassId);
	}
	// For Mocking Purposes
	public IPersistentObject getEventSource(){
		return eventSource;
	}
	protected Method getMethodByUuid(String uuid){
		Method[] methods = getEventSourceClass().getMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(NumlMetaInfo.class) && method.getAnnotation(NumlMetaInfo.class).uuid().equals(uuid)){
				return method;
			}
		}
		return null;// Error condition
	}
	public String getCallbackMethodUuid(){
		return callbackMethodUuid;
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
	public Integer getEventSourceClassId(){
		return this.eventSourceClassId;
	}
}
