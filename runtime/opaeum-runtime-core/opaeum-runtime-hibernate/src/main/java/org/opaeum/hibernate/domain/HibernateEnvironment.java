package org.opaeum.hibernate.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.internal.ConnectionProviderInitiator;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class HibernateEnvironment extends Environment{
	private ConversationalPersistence persistence;
	private UmtPersistence txPersistence;
	private SessionFactory sessionFactory;
	private Map<String,Object> components = new HashMap<String,Object>();
	private Session hibernateSession;
	private CmtPersistence cmtPersistence;
	public <T>void mockComponent(Class<T> clazz,T component){
		this.components.put(clazz.getName(), component);
	}

	@Override
	public <T>T getComponentImpl(Class<T> clazz,Annotation qualifiers){
		throw new IllegalArgumentException("Qualifiers is not yet supported in the domain environment");
	}
	private Session openHibernateSession(){
		if(this.sessionFactory == null){
			Set<String> schemas = new HashSet<String>();
			for(Class<?> class1:getMetaInfoMap().getAllClasses()){
				if(class1.isAnnotationPresent(Table.class)){
					schemas.add(class1.getAnnotation(Table.class).schema());
				}
			}
			schemas.remove(null);
			Map map= new HashMap<Object,Object>();
			Configuration hibernateConfiguration = new Configuration();
			hibernateConfiguration.configure(getHibernateConfigName());
			map.putAll(hibernateConfiguration.getProperties());
			ConnectionProvider connProvider =ConnectionProviderInitiator.INSTANCE.initiateService(map,(ServiceRegistryImplementor) new ServiceRegistryBuilder().applySettings(map) .buildServiceRegistry()); 
			try{
				Connection connection = connProvider.getConnection();
				Statement st = connection.createStatement();
				for(String string:schemas){
					try{
						st.executeUpdate("CREATE SCHEMA " + string + " AUTHORIZATION " + getProperty(Environment.DB_USER));
						connection.commit();
					}catch(Exception e){
					}
				}
			}catch(SQLException e1){
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sessionFactory = hibernateConfiguration.buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	public void reset(){
		// TODO this should not be necessary
		if(hibernateSession != null){
			hibernateSession.close();
			hibernateSession = null;
			persistence = null;
		}
		components.clear();
	}
	protected String getHibernateConfigName(){
		return loadProperties(PROPERTIES_FILE_NAME, getClass()).getProperty(HIBERNATE_CONFIG_NAME);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		Class<T> originalClass = IntrospectionUtil.getOriginalClass(o);
		if(originalClass == Object.class || originalClass == Proxy.class){
			// Interface
			for(Class<?> class1:o.getClass().getInterfaces()){
				if(!(class1.getName().startsWith("java") || class1.getName().startsWith("org.jboss") || class1.getName().startsWith("org.opaeum"))){
					return (Class<T>) class1;// return most significant
					// interface
				}
			}
		}
		return originalClass;
	}
	public ConversationalPersistence getPersistence(){
		if(persistence == null){
			persistence = new HibernateConversationalPersistence(openHibernateSession(),this);
		}
		return persistence;
	}
	public UmtPersistence getUmtPersistence(){
		if(txPersistence == null){
			txPersistence = new HibernateUmtPersistence(openHibernateSession(),this){
				@Override
				public void close(){
					super.close();
					txPersistence = null;
				}
			};
		}
		return txPersistence;
	}
	@Override
	public CmtPersistence getCurrentPersistence(){
		if(cmtPersistence == null){
			cmtPersistence = new HibernateCmtPersistence(null,this){
				@Override
				protected Session getSession(){
					return sessionFactory.getCurrentSession();
				}
			};
		}
		return cmtPersistence;
	}
	@Override
	public void endRequestContext(){
	}
	@Override
	public void startRequestContext(){
	}

	@Override
	public UmtPersistence createUmtPersistence(){
		return new HibernateUmtPersistence(openHibernateSession(),this);
	}
	
	@Override
	public ConversationalPersistence createConversationalPersistence(){
		return new HibernateConversationalPersistence(openHibernateSession(),this);
	}
}
