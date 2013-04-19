package org.opaeum.runtime.ejb3;

import java.lang.reflect.Field;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public class EnvironmentInterceptor{
	@AroundInvoke
	public Object mdbInterceptor(InvocationContext ctx) throws Exception{
		Class<? extends Object> oc = IntrospectionUtil.getOriginalClass(ctx.getTarget());
		UmtPersistence umtPersistence = null;
		for(Field field:oc.getDeclaredFields()){
			if(Environment.class.isAssignableFrom(field.getType())){
				field.setAccessible(true);
				field.set(ctx.getTarget(), Environment.getEnvironment((Class<? extends Environment>) field.getType()));
			}else if(field.getAnnotation(FromEnvironment.class) != null){
				field.setAccessible(true);
					Environment env = null;
					Class<? extends Environment> source = field.getAnnotation(FromEnvironment.class).source();
					if(source == Environment.class){
						env = Environment.getDefaultEnvironment(oc);
					}else{
						env = Environment.getEnvironment(source);
					}
					if(CmtPersistence.class == field.getType()){
						field.set(ctx.getTarget(), env.getCurrentPersistence());
					}else if(UmtPersistence.class == field.getType()){
						umtPersistence = env.createUmtPersistence();
						field.set(ctx.getTarget(), umtPersistence);
					}else if(ConversationalPersistence.class == field.getType()){
						field.set(ctx.getTarget(), env.createConversationalPersistence());
						// TODO autoflush and close on @Remove
					}
			}
		}
		try{
			return ctx.proceed();
		}finally{
			if(umtPersistence != null && umtPersistence.isOpen()){
				if(umtPersistence.isActive()){
					try{
						umtPersistence.rollbackTransaction();
					}catch(Exception e){
						
					}
				}
				umtPersistence.close();
			}
		}
	}
}
