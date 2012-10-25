package org.nakeduml.environment;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

public abstract class PassByValueController {
	protected NakedGraph db;
	public NakedGraph getDb() {
		return db;
	}

	public void setDb(NakedGraph db) {
		this.db = db;
	}

	protected void after() {
		GraphDb.remove();
	}

	protected void before() {
		if (db == null) {
			db = getInstance(NakedGraph.class, new AnnotationLiteral<ApplicationScopedDb>() {
				private static final long serialVersionUID = 2014690906756249924L;
			});
		}
		GraphDb.setDb(db);
		db.setMaxBufferSize(0);
	}

	protected BeanManager getBeanManager() {
		return BeanManagerProvider.getInstance().getBeanManager();
	}

	@SuppressWarnings("unchecked")
	protected <T> T getInstance(Class<T> type, Annotation... qualifiers) {
		BeanManager beanManager = getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, qualifiers));
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		return (T) beanManager.getReference(bean, type, ctx);
	}

}
