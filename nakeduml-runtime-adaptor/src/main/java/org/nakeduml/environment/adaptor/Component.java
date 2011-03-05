package org.nakeduml.environment.adaptor;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.seam.solder.beanManager.BeanManagerAware;
import org.jboss.seam.solder.literal.DefaultLiteral;

public class Component extends BeanManagerAware {

	public static Component INSTANCE = new Component();

	private Component() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> type) {
		BeanManager beanManager = getBeanManager();
        Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, DefaultLiteral.INSTANCE));
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        return (T)beanManager.getReference(bean, type, ctx);
	}
	
}
