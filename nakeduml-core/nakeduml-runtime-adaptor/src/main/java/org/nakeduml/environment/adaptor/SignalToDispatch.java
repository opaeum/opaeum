package org.nakeduml.environment.adaptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class SignalToDispatch extends org.nakeduml.environment.SignalToDispatch{
	private static final long serialVersionUID = -2996390224218437999L;
	public SignalToDispatch(Object source,ActiveObject target,AbstractSignal signal){
		super(source, target, signal);
	}
	public void prepareForDispatch(){
		try{
			this.source = source == duplicateWithId(this.source);
			this.target = (ActiveObject) duplicateWithId(this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					if(value instanceof ActiveObject){
						pd.getWriteMethod().invoke(signal, duplicateWithId((ActiveObject) value));
					}else if(value instanceof Set<?>){
						copyCollectionForDispatch(pd, new HashSet<Object>(), (Set<?>) value);
					}else if(value instanceof List<?>){
						copyCollectionForDispatch(pd, new ArrayList<Object>(), (List<?>) value);
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
	public void prepareForDelivery(Session session){
		try{
			this.source = resolve(session, this.source);
			this.target = (ActiveObject) resolve(session, this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for(PropertyDescriptor pd:properties){
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null){
					Object value = pd.getReadMethod().invoke(signal);
					if(value instanceof ActiveObject){
						pd.getWriteMethod().invoke(signal, resolve(session, (ActiveObject) value));
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
			Object result = em.get(ae.getClass(), enttity.getId());
			if(result==null){
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
	private void copyCollectionForDispatch(PropertyDescriptor pd,Collection<Object> newValue,Collection<?> oldValue) throws InstantiationException,
			IllegalAccessException,InvocationTargetException{
		for(Object o:oldValue){
			if(o instanceof ActiveObject){
				newValue.add(duplicateWithId((ActiveObject) o));
			}else{
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}
	private Object duplicateWithId(Object inputSource) throws InstantiationException,IllegalAccessException{
		if(inputSource instanceof ActiveObject){
			ActiveObject source =Environment.getInstance().getImplementationClass((ActiveObject)inputSource).newInstance();
			if(source instanceof AbstractEntity){
				AbstractEntity entity = (AbstractEntity) inputSource;
				if(entity.getId()==null){
					throw new IllegalStateException("entity " + entity.getClass().getName() + " does not have an id");
				}
				((AbstractEntity) source).setId(entity.getId());
				
			}
			return source;
		}else{
			return inputSource;
		}
	}
	public AbstractSignal getSignal(){
		return signal;
	}
	public Object getSource(){
		return source;
	}
	public ActiveObject getTarget(){
		return target;
	}
}
