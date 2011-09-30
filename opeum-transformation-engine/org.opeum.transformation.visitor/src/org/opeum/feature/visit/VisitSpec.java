package org.opeum.feature.visit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VisitSpec{
	private Class<?>[] match;
	private boolean matchSubclasses;
	public boolean isMatchSubclasses(){
		return matchSubclasses;
	}
	private Method m;
	public VisitSpec(){
	}
	public VisitSpec(Method m,boolean before){
		init(m, before);
	}
	public void init(Method m,boolean before){
		if(before){
			VisitBefore v = m.getAnnotation(VisitBefore.class);
			matchSubclasses = v.matchSubclasses();
			match = v.match();
		}else{
			VisitAfter v = m.getAnnotation(VisitAfter.class);
			matchSubclasses = v.matchSubclasses();
			match = v.match();
		}
		if(match.length == 0){
			match = new Class[]{
				m.getParameterTypes()[0]
			};
		}
		this.m = m;
	}
	public boolean resolvePeer(){
		return getParameterTypes().length == 2;
	}
	public boolean matches(Object o){
		if(matchSubclasses){
			for(Class<?> c:match){
				if(c.isInstance(o)){
					return true;
				}
			}
		}else{
			for(Class<?> c:match){
				if(c.isInterface()){
					// Only objects whose class directly implement the interaces
					Class<?>[] itfs = o.getClass().getInterfaces();
					for(Class<?> itf:itfs){
						if(itf == c){
							return true;
						}
					}
				}else if(c == o.getClass()){
					return true;
				}
			}
		}
		return false;
	}
	public void visit(VisitorAdapter<?,?> vi,Object[] o){
		try{
			getMethod(vi).invoke(vi, o);
		}catch(IllegalArgumentException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e);
		}
	}
	public Method getMethod(VisitorAdapter<?,?> va){
		return m;
	}
	private Class<?>[] getParameterTypes(){
		Class<?>[] paramTypes = m.getParameterTypes();
		Class<?>[] result = new Class[paramTypes.length];
		for(int i = 0;i < paramTypes.length;i++){
			result[i] = resolve(paramTypes[i]);
		}
		return paramTypes;
	}
	public Class<?> getPeerClass(){
		return m.getParameterTypes()[1];
	}
	private Class<?> resolve(Class<?> theClass){
		try{
			return Class.forName(theClass.getName());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public Class<?>[] getClassesToMatch(){
		return match;
	}
}
