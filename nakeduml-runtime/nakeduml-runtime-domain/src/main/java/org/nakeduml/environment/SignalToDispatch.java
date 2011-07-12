package org.nakeduml.environment;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.UUID;

import org.nakeduml.environment.marshall.PropertyValue;
import org.nakeduml.environment.marshall.Value;
import org.nakeduml.event.AsynchronouslyDelivered;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class SignalToDispatch implements AsynchronouslyDelivered{
	private static final long serialVersionUID = -2996390224218437999L;
	protected Value sourceValue;
	protected Value targetValue;
	private int retryCount;
	private String uid;
	private Integer signalClassId;
	private transient Object source;
	private transient IActiveObject target;
	private transient AbstractSignal signal;
	private Collection<PropertyValue> propertyValues;
	private boolean targetIsEntity;
	private String queueName;
	public SignalToDispatch(Object source,IActiveObject target,AbstractSignal signal){
		super();
		this.targetIsEntity = target instanceof IPersistentObject;
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
	public IActiveObject getTarget(){
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
		return getSignal().getClass().getSimpleName() + " to " + targetValue.getValueClass().getSimpleName();
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
		if(a instanceof IPersistentObject){
			((IPersistentObject) a).getId();
		}
	}
	public void prepareForDispatch(){
		this.propertyValues.clear();
		Class<? extends AbstractSignal> class1 = signal.getClass();
		this.signalClassId = Environment.getMetaInfoMap().getNakedUmlId(class1);
		propertyValues = Environment.getMetaInfoMap().getSecondaryObject(SignalMarshaller.class, class1).marshall(signal);
		this.sourceValue = Value.valueOf(this.source);
		this.targetValue = Value.valueOf(this.target);
		signal = null;
		source = null;
		target = null;
	}
	public void prepareForDelivery(AbstractPersistence session){
		if(this.sourceValue != null){
			this.source = Value.valueOf(sourceValue,session);
		}
		this.target = (IActiveObject) Value.valueOf(targetValue,session);
		JavaMetaInfoMap map = Environment.getMetaInfoMap();
		signal=map.getSecondaryObject(SignalMarshaller.class, map.getClass(signalClassId)).unmarshall(this.propertyValues);
	}
	public String getUid(){
		if(this.uid == null || this.uid.trim().length() == 0){
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
