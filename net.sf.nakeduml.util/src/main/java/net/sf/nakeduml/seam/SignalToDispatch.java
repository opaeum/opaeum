package net.sf.nakeduml.seam;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
				if (pd.getWriteMethod() != null) {
					Object value = pd.getReadMethod().invoke(signal);
					if (value instanceof AbstractEntity) {
						pd.getWriteMethod().invoke(signal, duplicateWithId((AbstractEntity) value));
					} else if (value instanceof Set<?>) {
						populateCollection(pd, new HashSet<Object>(), (Set<?>) value);
					} else if (value instanceof List<?>) {
						populateCollection(pd, new ArrayList<Object>(), (List<?>) value);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	private void populateCollection(PropertyDescriptor pd, Collection<Object> newValue, Collection<?> oldValue)
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
}
