package org.nakeduml.seam3.persistence;

import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionFactoryImpl;
import org.nakeduml.environment.Environment;
import org.nakeduml.hibernate.domain.PostgresDialect;

@ApplicationScoped
public class ManagedHibernateSessionFactoryProvider{
	private SessionFactoryImpl sessionFactory;
	@Produces
	@ApplicationScoped
	public SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			Configuration config = new Configuration();
			config.configure(Environment.loadProperties().getProperty(Environment.HIBERNATE_CONFIG_NAME));
			try{
				// TODO test if the dialect is indeed postgressql
				config.getTypeResolver().registerTypeOverride(PostgresDialect.PostgresqlMateralizedBlobType.INSTANCE);
				this.sessionFactory = (SessionFactoryImpl) config.buildSessionFactory();
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return this.sessionFactory;
	}
	@Produces
	@RequestScoped
	public Session getSession(){
		return this.sessionFactory.openSession();
	}
	@PreDestroy
	public void close(){
		this.sessionFactory.close();
	}
}
