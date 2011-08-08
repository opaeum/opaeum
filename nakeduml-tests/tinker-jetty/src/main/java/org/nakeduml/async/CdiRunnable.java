package org.nakeduml.async;

import java.lang.annotation.Annotation;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.context.ApplicationContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.apache.webbeans.util.WebBeansUtil;

public class CdiRunnable implements Runnable {

	@Inject
	Logger logger;
	InvocationContext ctx;
	ServletContext servletContext;
	
	public CdiRunnable(InvocationContext ctx, ServletContext servletContext) {
		super();
		this.ctx = ctx;
		this.servletContext = servletContext;
		
	}

	@SuppressWarnings("unchecked")
	private <T>T getInstance(Class<T> type,Annotation...qualifiers){
		BeanManager beanManager = BeanManagerProvider.getInstance().getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, qualifiers));
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, type, ctx);
	}

	@Override
	public void run() {
		ContainerLifecycle lifeCycle = null;
		try {
			lifeCycle = WebBeansContext.getInstance().getService(ContainerLifecycle.class);
			ServletRequestEvent servletRequestEvent = new ServletRequestEvent(servletContext, new MockServletRequest());
			lifeCycle.getContextService().startContext(RequestScoped.class, servletRequestEvent);
			Object o = getInstance(ctx.getTarget().getClass());
			ctx.getMethod().invoke(o, ctx.getParameters());
		} catch (Exception e) {
			e.printStackTrace();
			WebBeansUtil.throwRuntimeExceptions(e);
		} finally {
			lifeCycle.getContextService().endContext(RequestScoped.class, null);
		}
	}

}
