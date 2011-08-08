package org.nakeduml.async;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.ServletContext;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;

@Interceptor
@Asynchronous
public class AsynchronousInterceptor<T> implements Serializable {

	private static final long serialVersionUID = 6837498942582094612L;
	@Inject
	DispatchSignal dispatchSignal;
	@Inject
	FacesContext facesContext;

	@AroundInvoke
	public Object runAsync(InvocationContext ctx) throws Exception {
		if (facesContext.getCurrentInstance() != null) {
			ContainerLifecycle lifeCycle = WebBeansContext.getInstance().getService(ContainerLifecycle.class);
			dispatchSignal.submit(new CdiRunnable(ctx, (ServletContext) facesContext.getExternalContext().getContext()));
			return null;
		} else {
			return ctx.proceed();
		}
	}

}
