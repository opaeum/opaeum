package org.nakeduml.runtime.domain;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
public class IntrospectionUtil {
	public static MethodDescriptor getMethod(String name, Class c) {
		try {
			MethodDescriptor[] methods = Introspector.getBeanInfo(c, Object.class).getMethodDescriptors();
			for (int i = 0; i < methods.length; i++) {
				MethodDescriptor descriptor = methods[i];
				if (descriptor.getName().equals(name)) {
					return descriptor;
				}
			}
			return null;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
	public static PropertyDescriptor getProperty(String name, Class c) {
		try {
			PropertyDescriptor[] properties = Introspector.getBeanInfo(c).getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				PropertyDescriptor descriptor = properties[i];
				if (descriptor.getName().equals(name)) {
					return descriptor;
				}
			}
			return null;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
	public static PropertyDescriptor[] getProperties(Class c) {
		try {
			PropertyDescriptor[] properties = Introspector.getBeanInfo(c).getPropertyDescriptors();
			return properties;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
	public static Class<?> getOriginalClass(Object target) {
		Class<?> c = target.getClass();
		return getOriginalClass(c);
	}
	public static Class<?> getOriginalClass(Class<?> c) {
		while (c.getName().indexOf("$$") > -1) {
			c = c.getSuperclass();
		}
		return c;
	}
	public static Object get(PropertyDescriptor property, Object target) {
		try {
			return property.getReadMethod().invoke(target, new Object[0]);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw throwTarget(e);
		}
	}
	private static RuntimeException throwTarget(InvocationTargetException e) {
		if (e.getTargetException() instanceof RuntimeException) {
			throw (RuntimeException) e.getTargetException();
		} else if (e.getTargetException() instanceof Error) {
			throw (Error) e.getTargetException();
		} else {
			throw new RuntimeException(e.getTargetException());
		}
	}
	public static void set(PropertyDescriptor property, Object target, Object value) {
		try {
			if (property.getWriteMethod() != null) {
				property.getWriteMethod().invoke(target, new Object[] { value });
			} else {
				throw new RuntimeException("Property '" + property.getName() + "' is readOnly");
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw throwTarget(e);
		}
	}
//	public static EntityHelper createHelper(MetaIdentifiable selectedObject) {
//		try {
//			EntityHelper helper = null;
//			if (selectedObject instanceof AbstractEntity) {
//				String helperClassName = getOriginalClass(selectedObject).getName() + "Helper";
//				Class<? extends AbstractEntity> helperClass = (Class<? extends AbstractEntity>) Thread.currentThread().getContextClassLoader().loadClass(helperClassName);
//				helper = (EntityHelper) helperClass.newInstance();
//				helper.setSelectedObject((AbstractEntity) selectedObject);
//			}
//			helper.setUser(CurrentUser.getCurrentUser());
//			return helper;
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}
	private static String getSimpleName(Class c) {
		if (c.getName().indexOf('.') == -1) {
			return c.getName();
		} else {
			return c.getName().substring(c.getName().lastIndexOf('.') + 1);
		}
	}
	public static void main(String[] args) {
		System.out.println(getSimpleName(String.class));
	}
	public static Class getStateClass(Class entityClass) {
		try {
			String stateClassName = entityClass.getPackage().getName() + "." + getSimpleName(entityClass).toLowerCase()
					+ "states";
			stateClassName += "." + getSimpleName(entityClass) + "State";
			Class stateClass = Thread.currentThread().getContextClassLoader().loadClass(stateClassName);
			return stateClass;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	public static AbstractEnum[] getAllLiterals(Class enumClass) {
		try {
			return (AbstractEnum[]) enumClass.getMethod("getAllLiterals", new Class[0]).invoke(null, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Class getClass(String entityToCreate) {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(entityToCreate);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
