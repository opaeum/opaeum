package org.nakeduml.environment;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.event.Retryable;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class SignalToDispatch implements Retryable{
	private static final long serialVersionUID = -2996390224218437999L;
	protected Value sourceValue;
	protected Value targetValue;
	private int retryCount;
	private String uid;
	private Integer signalClassId;
	private transient Object source;
	private transient ActiveObject target;
	private transient AbstractSignal signal;
	private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	private boolean targetIsEntity;
	private String queueName;
	public SignalToDispatch(Object source,ActiveObject target,AbstractSignal signal){
		super();
		this.targetIsEntity = target instanceof AbstractEntity;
		this.queueName = "queue/" + signal.getClass().getName();
		this.source = source;
		this.target = target;
		this.signal = signal;
	}
	public boolean targetIsEntity(){
		return targetIsEntity;
	}
	public SignalToDispatch(){
	}
	public AbstractSignal getSignal(){
		return signal;
	}
	public String getQueueName(){
		return queueName;
	}
	public Object getSource(){
		return source;
	}
	public ActiveObject getTarget(){
		return target;
	}
	public int getRetryCount(){
		return retryCount;
	}
	public void incrementRetryCount(){
		retryCount++;
	}
	@Override
	public String getDescription(){
		return getSignal().getClass().getSimpleName() + " to " + getTarget().getClass().getSimpleName();
	}
	public void retrieveIds(){
		try{
			retrieveId(source);
			retrieveId(target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					// TODO make recursive for dataobjects
					if(value instanceof Collection<?>){
						Collection<?> col = (Collection<?>) value;
						for(Object object:col){
							retrieveId(object);
						}
					}else{
						retrieveId(value);
					}
				}
			}
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}
	}
	private void retrieveId(Object a){
		if(a instanceof AbstractEntity){
			((AbstractEntity) a).getId();
		}
	}
	public void prepareForDispatch(){
		try{
			this.propertyValues.clear();
			Class<? extends AbstractSignal> class1 = signal.getClass();
			NumlMetaInfo annotation = class1.getAnnotation(NumlMetaInfo.class);
			this.signalClassId = annotation.nakedUmlId();
			this.sourceValue = Value.valueOf(this.source);
			this.targetValue = Value.valueOf(this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(class1);
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null && pd.getReadMethod().getAnnotation(NumlMetaInfo.class) != null){
					Object propertyValue = pd.getReadMethod().invoke(signal);
					propertyValues.add(new PropertyValue(pd, Value.valueOf(propertyValue)));
				}
			}
			signal = null;
			source = null;
			target = null;
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}
	}
	public void prepareForDelivery(Session session){
		if(this.sourceValue != null){
			this.source = sourceValue.getValue(session);
		}
		this.target = (ActiveObject) targetValue.getValue(session);
		this.signal = (AbstractSignal) IntrospectionUtil.newInstance(Environment.getMetaInfoMap().getClass(signalClassId));
		for(PropertyValue signalProperty:this.propertyValues){
			signalProperty.setValue(signal, session);
		}
	}
	public String getUid(){
		if(this.uid == null || this.uid.trim().length() == 0){
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
