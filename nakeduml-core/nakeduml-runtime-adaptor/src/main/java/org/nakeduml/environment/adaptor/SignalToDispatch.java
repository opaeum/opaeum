package org.nakeduml.environment.adaptor;

import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.adaptor.SignalMdb.ObjectToLock;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractProcess;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class SignalToDispatch extends org.nakeduml.environment.SignalToDispatch{
	private static final long serialVersionUID = -2996390224218437999L;
	private String uid;
	private int retryCount;
	private ObjectToLock processToLock;
	public SignalToDispatch(){
		super(null, null, null);
	}
	public SignalToDispatch(Object source,ActiveObject target,AbstractSignal signal){
		super(source, target, signal);
	}
	public int getRetryCount(){
		return retryCount;
	}
	public void incrementRetryCount(){
		retryCount++;
	}
	public ObjectToLock getObjectToLock(){
		return processToLock;
	}
	public void retrieveIds(){
		try{
			MethodDescriptor method = IntrospectionUtil.getMethod("getContextObject", IntrospectionUtil.getOriginalClass(target));
			if(target instanceof AbstractProcess && method != null){
				AbstractEntity e = (AbstractEntity) method.getMethod().invoke(target);
				processToLock = new ObjectToLock((Class<? extends AbstractEntity>) method.getMethod().getReturnType(), e.getId());
			}
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
			this.source = duplicate(this.source);
			this.target = (ActiveObject) duplicate(this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					// TODO make recursive for dataobjects
					if(value instanceof ActiveObject || value instanceof AbstractEntity){
						Object duplicate = duplicate(value);
						pd.getWriteMethod().invoke(signal, duplicate);
					}else if(value instanceof Set<?>){
						duplicateCollectionForDispatch(pd, new HashSet<Object>(), (Set<?>) value);
					}else if(value instanceof List<?>){
						duplicateCollectionForDispatch(pd, new ArrayList<Object>(), (List<?>) value);
					}
				}
			}
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}
	}
	private Object duplicate(Object object) throws InstantiationException,IllegalAccessException{
		if(object instanceof AbstractEntity){
			return duplicateWithId((AbstractEntity) object);
		}else if(object instanceof ActiveObject){
			return duplicateImplementation((ActiveObject) object);
		}else{
			return object;
		}
	}
	public void prepareForDelivery(Session session){
		try{
			this.source = resolve(session, this.source);
			this.target = (ActiveObject) resolve(session, this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					if(value instanceof ActiveObject || value instanceof AbstractEntity){
						pd.getWriteMethod().invoke(signal, resolve(session, value));
					}else if(value instanceof Set<?>){
						resolveCollectionOnDelivery(session, pd, new HashSet<Object>(), (Set<?>) value);
					}else if(value instanceof List<?>){
						resolveCollectionOnDelivery(session, pd, new ArrayList<Object>(), (List<?>) value);
					}
				}
			}
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}
	}
	private void resolveCollectionOnDelivery(Session em,PropertyDescriptor pd,Collection<Object> newValue,Collection<?> oldValue) throws InstantiationException,
			IllegalAccessException,InvocationTargetException{
		for(Object o:oldValue){
			if(o instanceof ActiveObject || o instanceof AbstractEntity){
				newValue.add(resolve(em, o));
			}else{
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}
	private Object resolve(Session em,Object ae){
		if(ae instanceof AbstractEntity){
			AbstractEntity enttity = (AbstractEntity) ae;
			Object result = em.get(IntrospectionUtil.getOriginalClass(ae), enttity.getId());
			if(result == null){
				throw new IllegalStateException(ae.getClass().getSimpleName() + ":" + enttity.getId() + " could not be found!");
			}
			return result;
		}else if(ae instanceof ActiveObject){
			Class<?> originalClass = IntrospectionUtil.getOriginalClass(ae);
			if(originalClass == Object.class){
				// Would have an interface
				return Environment.getInstance().getComponent(ae.getClass().getInterfaces()[0]);
			}else{
				return Environment.getInstance().getComponent(originalClass);
			}
		}else{
			return ae;
		}
	}
	private void duplicateCollectionForDispatch(PropertyDescriptor pd,Collection<Object> newValue,Collection<?> oldValue) throws InstantiationException,
			IllegalAccessException,InvocationTargetException{
		for(Object o:oldValue){
			if(o instanceof AbstractEntity || o instanceof ActiveObject){
				newValue.add(duplicate((AbstractEntity) o));
			}else{
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}
	private Object duplicateWithId(AbstractEntity inputSource) throws InstantiationException,IllegalAccessException{
		Class<AbstractEntity> implementationClass = IntrospectionUtil.getOriginalClass(inputSource);
		AbstractEntity copy = implementationClass.newInstance();
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((AbstractEntity) inputSource).getClass().getName() + " does not have an id");
		}
		copy.setId(inputSource.getId());
		return copy;
	}
	private Object duplicateImplementation(ActiveObject inputSource) throws InstantiationException,IllegalAccessException{
		Environment instance = Environment.getInstance();
		Class<ActiveObject> implementationClass = instance.getImplementationClass(inputSource);
		ActiveObject copy = implementationClass.newInstance();
		return copy;
	}
	public String getUid(){
		if(this.uid == null || this.uid.trim().length() == 0){
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
