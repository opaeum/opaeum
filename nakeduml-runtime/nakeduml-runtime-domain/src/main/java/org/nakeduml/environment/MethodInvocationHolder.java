package org.nakeduml.environment;

import java.util.Collection;

import org.nakeduml.environment.marshall.PropertyValue;
import org.nakeduml.environment.marshall.Value;
import org.nakeduml.event.AsynchronouslyDelivered;
import org.nakeduml.runtime.domain.IPersistentObject;

//A holder for async method invocations to avoid serialization exceptions when the methodInvoker has changed
public class MethodInvocationHolder implements AsynchronouslyDelivered{
	private static final long serialVersionUID = -299639022421437999L;
	transient MethodInvoker methodInvoker;
	private transient Object target;
	private String methodUuid;
	private Collection<PropertyValue> propertyValues;
	private int retryCount;
	private boolean targetIsEntity;
	private Value targetValue;
	public MethodInvocationHolder(Object target,MethodInvoker mi){
		this.methodInvoker = mi;
		targetIsEntity = target instanceof IPersistentObject;
		this.target = target;
	}
	@Override
	public String getDescription(){
		return methodInvoker.getClass().getSimpleName();
	}
	@Override
	public int getRetryCount(){
		return retryCount;
	}
	@Override
	public void incrementRetryCount(){
		retryCount++;
	}
	public void prepareForDispatch(){
		this.propertyValues.clear();
		this.targetValue = Value.valueOf(target);
		this.methodUuid = methodInvoker.getUuid();
		propertyValues = methodInvoker.marshall();
		methodInvoker = null;
	}
	public void prepareForDelivery(AbstractPersistence session){
		JavaMetaInfoMap map = Environment.getMetaInfoMap();
		methodInvoker = map.getMethodInvoker(methodUuid);
		methodInvoker.unmarshall(this.propertyValues,session);
		target = Value.valueOf(targetValue, session);
	}
	public String getQueueName(){
		return methodInvoker.getQueueName();
	}
	public boolean targetIsEntity(){
		return this.targetIsEntity;
	}
	public void invoke(){
		methodInvoker.invoke(target);
	}
}
