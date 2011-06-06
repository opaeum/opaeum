package org.nakeduml.environment;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.nakeduml.event.Retryable;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class SignalToDispatch implements Retryable{
	private static final long serialVersionUID = -2996390224218437999L;
	protected AbstractSignal signal;
	protected Object source;
	protected IActiveObject target;
	private int retryCount;
	private String uid;
	public SignalToDispatch(Object source,IActiveObject target,AbstractSignal signal){
		super();
		this.source = source;
		this.target = target;
		this.signal = signal;
	}
	public SignalToDispatch(){
	}
	public AbstractSignal getSignal(){
		return signal;
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
		if(a instanceof IPersistentObject){
			((IPersistentObject) a).getId();
		}
	}
	public void prepareForDispatch(){
		try{
			this.source = duplicate(this.source);
			this.target = (IActiveObject) duplicate(this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					// TODO make recursive for dataobjects
					if(value instanceof IActiveObject || value instanceof IPersistentObject){
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
		if(object instanceof IPersistentObject){
			return duplicateWithId((IPersistentObject) object);
		}else if(object instanceof IActiveObject){
			return duplicateImplementation((IActiveObject) object);
		}else{
			return object;
		}
	}
	public void prepareForDelivery(Session session){
		try{
			this.source = resolve(session, this.source);
			this.target = (IActiveObject) resolve(session, this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					if(value instanceof IActiveObject || value instanceof IPersistentObject){
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
			if(o instanceof IActiveObject || o instanceof IPersistentObject){
				newValue.add(resolve(em, o));
			}else{
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}
	private Object resolve(Session em,Object ae){
		if(ae instanceof IPersistentObject){
			IPersistentObject enttity = (IPersistentObject) ae;
			Object result = em.get(IntrospectionUtil.getOriginalClass(ae), enttity.getId());
			if(result == null){
				throw new IllegalStateException(ae.getClass().getSimpleName() + ":" + enttity.getId() + " could not be found!");
			}
			return result;
		}else if(ae instanceof IActiveObject){
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
			if(o instanceof IPersistentObject || o instanceof IActiveObject){
				newValue.add(duplicate((IPersistentObject) o));
			}else{
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}
	private Object duplicateWithId(IPersistentObject inputSource) throws InstantiationException,IllegalAccessException{
		Class<IPersistentObject> implementationClass = IntrospectionUtil.getOriginalClass(inputSource);
		IPersistentObject copy = implementationClass.newInstance();
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((IPersistentObject) inputSource).getClass().getName() + " does not have an id");
		}
		copy.setId(inputSource.getId());
		return copy;
	}
	private Object duplicateImplementation(IActiveObject inputSource) throws InstantiationException,IllegalAccessException{
		Environment instance = Environment.getInstance();
		Class<IActiveObject> implementationClass = instance.getImplementationClass(inputSource);
		IActiveObject copy = implementationClass.newInstance();
		return copy;
	}
	public String getUid(){
		if(this.uid == null || this.uid.trim().length() == 0){
			uid = UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
