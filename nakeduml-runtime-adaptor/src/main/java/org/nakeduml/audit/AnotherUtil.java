package org.nakeduml.audit;

public class AnotherUtil {
	public static Class<?> getOriginalClass(Object target) {
		Class<?> c = target.getClass();
		return getOriginalClass(c);
	}

	@SuppressWarnings("unchecked")
	public  static <T> Class<T> getOriginalClass(Class<T> c) {
		while (c.getName().indexOf("$$") > -1) {
			c = (Class<T>) c.getSuperclass();
		}
		return c;
	}
	
}
