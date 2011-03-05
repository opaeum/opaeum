package net.sf.nakeduml.seam;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;
public class SignalToDispatch extends org.nakeduml.environment.SignalToDispatch {
	private static final long serialVersionUID = 1986170768555066115L;

	public SignalToDispatch(Object source, ActiveObject target, AbstractSignal signal) {
		super(source,target,signal);
	}
//
//	public void prepareForDispatch() {
//		try {
//			this.source = duplicateWithId(this.source);
//			this.target = (ActiveObject) duplicateWithId(this.target);
//			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
//			for (PropertyDescriptor pd : properties) {
//				if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
//					Object value = pd.getReadMethod().invoke(signal);
//					if (value instanceof ActiveObject) {
//						pd.getWriteMethod().invoke(signal, duplicateWithId((ActiveObject) value));
//					} else if (value instanceof Set<?>) {
//						copyCollectionForDispatch(pd, new HashSet<Object>(), (Set<?>) value);
//					} else if (value instanceof List<?>) {
//						copyCollectionForDispatch(pd, new ArrayList<Object>(), (List<?>) value);
//					}
//				}
//			}
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (InvocationTargetException e) {
//			throw new RuntimeException(e.getTargetException());
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void prepareForDelivery(EntityManager em) {
//		try {
//			this.source = resolve(em, this.source);
//			this.target = (ActiveObject) resolve(em, this.target);
//			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
//			for (PropertyDescriptor pd : properties) {
//				if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
//					Object value = pd.getReadMethod().invoke(signal);
//					if (value instanceof ActiveObject) {
//						pd.getWriteMethod().invoke(signal, resolve(em, (ActiveObject) value));
//					} else if (value instanceof Set<?>) {
//						resolveCollectionOnDelivery(em, pd, new HashSet<Object>(), (Set<?>) value);
//					} else if (value instanceof List<?>) {
//						resolveCollectionOnDelivery(em, pd, new ArrayList<Object>(), (List<?>) value);
//					}
//				}
//			}
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (InvocationTargetException e) {
//			throw new RuntimeException(e.getTargetException());
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private void resolveCollectionOnDelivery(EntityManager em, PropertyDescriptor pd, Collection<Object> newValue, Collection<?> oldValue)
//			throws InstantiationException, IllegalAccessException, InvocationTargetException {
//		for (Object o : oldValue) {
//			if (o instanceof ActiveObject || o instanceof AbstractEntity) {
//				newValue.add(resolve(em, o));
//			} else {
//				newValue.add(o);
//			}
//		}
//		pd.getWriteMethod().invoke(signal, newValue);
//	}
//
//	private Object resolve(EntityManager em, Object ae) {
//		if (ae instanceof AbstractEntity) {
//			return em.find(ae.getClass(), ((AbstractEntity) ae).getId());
//		} else {
//			Class<?> originalClass = IntrospectionUtil.getOriginalClass(ae);
//			if (originalClass.isAnnotationPresent(Name.class)) {
//				if (Contexts.isEventContextActive() || Contexts.isApplicationContextActive()) {
//					return Component.getInstance(originalClass.getAnnotation(Name.class).value());
//				} else {
//					return ae;
//				}
//			} else {
//				throw new IllegalStateException("Only jpa entities and seam components can receive signals");
//			}
//		}
//	}
//
//	private void copyCollectionForDispatch(PropertyDescriptor pd, Collection<Object> newValue, Collection<?> oldValue)
//			throws InstantiationException, IllegalAccessException, InvocationTargetException {
//		for (Object o : oldValue) {
//			if (o instanceof ActiveObject) {
//				newValue.add(duplicateWithId((ActiveObject) o));
//			} else {
//				newValue.add(o);
//			}
//		}
//		pd.getWriteMethod().invoke(signal, newValue);
//	}
//
//	private Object duplicateWithId(Object inputSource) throws InstantiationException, IllegalAccessException {
//		Object source = IntrospectionUtil.getOriginalClass(inputSource).newInstance();
//		if (source instanceof AbstractEntity) {
//			((AbstractEntity) source).setId(((AbstractEntity) inputSource).getId());
//		}
//		return source;
//	}
//
//	public AbstractSignal getSignal() {
//		return signal;
//	}
//
//	public Object getSource() {
//		return source;
//	}
//
//	public ActiveObject getTarget() {
//		return target;
//	}
}
