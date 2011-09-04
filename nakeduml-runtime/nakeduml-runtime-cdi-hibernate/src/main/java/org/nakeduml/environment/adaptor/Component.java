package org.nakeduml.environment.adaptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.seam.solder.beanManager.BeanManagerAware;
import org.jboss.seam.solder.literal.DefaultLiteral;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class Component extends BeanManagerAware{
	public static Component INSTANCE = new Component();
	private Component(){
		super();
	}
	@SuppressWarnings("unchecked")
	public <T>T getInstance(Class<T> type){
		BeanManager beanManager = getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, DefaultLiteral.INSTANCE));
		if(bean == null){
			throw new IllegalStateException(type + " is not deployed to CDI");
		}
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, type, ctx);
	}
	@SuppressWarnings("unchecked")
	public <T>Class<T> getImplementationClass(T o){
		if(!o.getClass().isSynthetic() && !o.getClass().getName().contains("$$")){
			return (Class<T>) o.getClass();
		}else{
			Class<T> c = (Class<T>) IntrospectionUtil.getOriginalClass(o);
			if(c == Object.class || c == Proxy.class){
				// injected by interface
				BeanManager beanManager = getBeanManager();
				for(Class<?> class1:o.getClass().getInterfaces()){
					if(!(class1.getName().startsWith("java") || class1.getName().startsWith("org.jboss") || class1.getName().startsWith(
							"org.nakeduml"))){
						Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(class1, DefaultLiteral.INSTANCE));
						Set<Type> types = bean.getTypes();
						for(Type type:types){
							if(type instanceof Class && !((Class<?>) type).isInterface() && type != Object.class){
								return (Class<T>) type;
							}
						}
						return (Class<T>) class1;// return most significant interface
					}
				}
			}
			return c;// You're fucked
		}
	}
	@SuppressWarnings("unchecked")
	public <T>T getInstance(Class<T> type,Annotation...qualifiers){
		BeanManager beanManager = getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, qualifiers));
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, type, ctx);
	}
}
