package net.sf.nakeduml.seam;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractSignal;
import net.sf.nakeduml.util.IntrospectionUtil;

public class SignalToDispatch implements Serializable {
	private static final long serialVersionUID = -2996390224218437999L;
	private AbstractSignal signal;
	private AbstractEntity source;
	private AbstractEntity target;

	public SignalToDispatch(AbstractEntity source, AbstractEntity target, AbstractSignal signal) {
		super();
		this.signal = signal;
		this.source = source;
		this.target = target;
	}

	public void prepareForDispatch() {
		try {
			this.source = duplicateWithId(this.source);
			this.target = duplicateWithId(this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for (PropertyDescriptor pd : properties) {
				if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
					Object value = pd.getReadMethod().invoke(signal);
					if (value instanceof AbstractEntity) {
						pd.getWriteMethod().invoke(signal, duplicateWithId((AbstractEntity) value));
					} else if (value instanceof Set<?>) {
						copyCollectionForDispatch(pd, new HashSet<Object>(), (Set<?>) value);
					} else if (value instanceof List<?>) {
						copyCollectionForDispatch(pd, new ArrayList<Object>(), (List<?>) value);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException());
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	public void prepareForDelivery(EntityManager em) {
		try {
			this.source = resolve(em, this.source);
			this.target = resolve(em, this.target);
			PropertyDescriptor[] properties = IntrospectionUtil.getProperties(signal.getClass());
			for (PropertyDescriptor pd : properties) {
				if (pd.getWriteMethod() != null && pd.getReadMethod() != null) {
					Object value = pd.getReadMethod().invoke(signal);
					if (value instanceof AbstractEntity) {
						pd.getWriteMethod().invoke(signal, resolve(em, (AbstractEntity) value));
					} else if (value instanceof Set<?>) {
						resolveCollectionOnDelivery(em, pd, new HashSet<Object>(), (Set<?>) value);
					} else if (value instanceof List<?>) {
						resolveCollectionOnDelivery(em, pd, new ArrayList<Object>(), (List<?>) value);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException());
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	private void resolveCollectionOnDelivery(EntityManager em, PropertyDescriptor pd, Collection<Object> newValue, Collection<?> oldValue)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		for (Object o : oldValue) {
			if (o instanceof AbstractEntity) {
				newValue.add(duplicateWithId((AbstractEntity) o));
			} else {
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}

	private AbstractEntity resolve(EntityManager em, AbstractEntity ae) {
		return em.find(ae.getClass(), ae.getId());
	}

	private void copyCollectionForDispatch(PropertyDescriptor pd, Collection<Object> newValue, Collection<?> oldValue)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		for (Object o : oldValue) {
			if (o instanceof AbstractEntity) {
				newValue.add(duplicateWithId((AbstractEntity) o));
			} else {
				newValue.add(o);
			}
		}
		pd.getWriteMethod().invoke(signal, newValue);
	}

	private AbstractEntity duplicateWithId(AbstractEntity inputSource) throws InstantiationException, IllegalAccessException {
		AbstractEntity source = (AbstractEntity) IntrospectionUtil.getOriginalClass(inputSource).newInstance();
		source.setId(this.source.getId());
		return source;
	}
	public AbstractSignal getSignal() {
		return signal;
	}

	public AbstractEntity getSource() {
		return source;
	}

	public AbstractEntity getTarget() {
		return target;
	}

}
