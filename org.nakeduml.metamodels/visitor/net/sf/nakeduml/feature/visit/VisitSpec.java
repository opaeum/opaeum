package net.sf.nakeduml.feature.visit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//TODO figure out why we still get those funny exceptions after code modifications when debugging a VisitAdapter.
public class VisitSpec {
	private Class[] match;
	private boolean matchSubclasses;
	private Method m;
	private boolean relink;

	public VisitSpec(Method m, boolean before) {
		if (before) {
			VisitBefore v = m.getAnnotation(VisitBefore.class);
			matchSubclasses = v.matchSubclasses();
			match = v.match();
		} else {
			VisitAfter v = m.getAnnotation(VisitAfter.class);
			matchSubclasses = v.matchSubclasses();
			match = v.match();
		}
		if (match.length == 0) {
			match = new Class[] { m.getParameterTypes()[0] };
		}
		this.m = m;
	}

	public boolean resolvePeer() {
		return getParameterTypes().length == 2;
	}

	public boolean matches(Object o) {
		if (matchSubclasses) {
			for (Class c : match) {
				if (c.isInstance(o)) {
					return true;
				}
			}
		} else {
			for (Class c : match) {
				if (c.isInterface()) {
					// Only objects whose class directly implement the interaces
					Class[] itfs = o.getClass().getInterfaces();
					for (Class itf : itfs) {
						if (itf == c) {
							return true;
						}
					}
				} else if (c == o.getClass()) {
					return true;
				}
			}
		}
		return false;
	}

	public void visit(VisitorAdapter vi, Object... o) {
		try {
			getMethod(vi).invoke(vi, o);
			relink = false;
		} catch (IllegalArgumentException e) {
			if (!relink) {
				System.out.println("relinking visitSpec");
				this.relink = true;
				visit(vi, o);
			} else {
				try {
					System.out.println(getMethod(vi).getDeclaringClass().isInstance(vi));
					for (int i = 0; i < o.length; i++) {
						System.out.println(getMethod(vi).getParameterTypes()[i].isInstance(o[i]));
					}
					getMethod(vi).invoke(vi, o);
				} catch (Exception e1) {
					throw new RuntimeException(e1);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private Method getMethod(VisitorAdapter va) {
		if (relink) {
			try {
				m= Thread.currentThread().getContextClassLoader().loadClass(va.getClass().getName()).getDeclaredMethod(m.getName(), getParameterTypes());
				return m;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			return m;
		}
	}

	private Class<?>[] getParameterTypes() {
		Class<?>[] paramTypes = m.getParameterTypes();
		Class[] result = new Class[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			result[i] = resolve(paramTypes[i]);
		}
		return paramTypes;
	}

	public Class getPeerClass() {
		if (relink) {
			Class<?> theClass = m.getParameterTypes()[1];
			return resolve(theClass);
		} else {
			return m.getParameterTypes()[1];
		}
	}

	private Class resolve(Class<?> theClass) {
		try {
			return Class.forName(theClass.getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
