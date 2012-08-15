package org.opaeum.runtime.jpa;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.hibernate.Session;
import org.opaeum.hibernate.domain.EventOccurrence;
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.runtime.event.INotificationService;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public class StandaloneJpaEnvironment extends Environment{
	Map<Class<?>,Object> components = new HashMap<Class<?>,Object>();
	private static EntityManagerFactory entityManagerFactory;
	private StandaloneJpaUmtPersistence txPersistence;
	private StandaloneJpaCmtPersistence cmtPersistence;
	private StandaloneJpaConversationalPersistence persistence;
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	public static StandaloneJpaEnvironment getInstance(){
		defaultImplementation = StandaloneJpaEnvironment.class;
		return (StandaloneJpaEnvironment) Environment.getInstance();
	}
	private EntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory == null){
			Set<String> schemas = new HashSet<String>();
			for(Class<?> class1:getMetaInfoMap().getAllClasses()){
				if(class1.isAnnotationPresent(Table.class)){
					schemas.add(class1.getAnnotation(Table.class).schema());
				}
			}
			schemas.remove(null);
			schemas.remove("");
			try{
				loadDriver("org.hsqldb.jdbcDriver");
				loadDriver("org.postgres.Driver");
				loadDriver("oracle.jdbc.driver.OracleDriver");
				loadDriver("com.ibm.db2.jcc.DB2Driver");
				loadDriver("org.gjt.mm.mysql.Driver");
				// TODO etc
				Connection connection = DriverManager.getConnection(super.getProperty(JDBC_CONNECTION_URL),
						Environment.getInstance().getProperty(Environment.DB_USER, "sa"), Environment.getInstance().getProperty(Environment.DB_PASSWORD, ""));
				Statement st = connection.createStatement();
				for(String string:schemas){
					try{
						st.executeUpdate("CREATE SCHEMA " + string + " AUTHORIZATION DBA");
						//TODO make this db-independent
						connection.commit();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			entityManagerFactory = Persistence.createEntityManagerFactory(getProperty(HIBERNATE_CONFIG_NAME));
		}
		return entityManagerFactory;
	}
	private void loadDriver(String driver){
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e1){
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>T getComponent(Class<T> clazz){
		if(components.get(clazz.getName()) != null){
			return (T) components.get(clazz.getName());
		}else if(clazz == ConversationalPersistence.class){
			return (T) getPersistence();
		}else if(clazz == CmtPersistence.class){
			return (T) getCmtPersistence();
		}else if(clazz == UmtPersistence.class){
			return (T) getUmtPersistence();
		}else{
			return (T) components.get(clazz);
		}
	}
	public ConversationalPersistence getPersistence(){
		if(persistence == null){
			persistence = new StandaloneJpaConversationalPersistence(getEntityManager());
		}
		return persistence;
	}
	private EntityManager getEntityManager(){
		if(this.entityManager == null){
			entityManager = openHibernateSession();
			((Session) entityManager.getDelegate()).enableFilter("noDeletedObjects");
		}
		return entityManager;
	}
	public StandaloneJpaUmtPersistence getUmtPersistence(){
		if(txPersistence == null){
			txPersistence = new StandaloneJpaUmtPersistence(getEntityManager());
		}
		return txPersistence;
	}
	private EntityManager openHibernateSession(){
		return getEntityManagerFactory().createEntityManager();
	}
	public CmtPersistence getCmtPersistence(){
		if(cmtPersistence == null){
			cmtPersistence = new StandaloneJpaCmtPersistence(getEntityManager());
		}
		return cmtPersistence;
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return getComponent(clazz);
	}
	@Override
	public void reset(){
		this.entityManager = null;
		this.cmtPersistence = null;
		this.persistence = null;
		this.txPersistence = null;
	}
	@Override
	public void endRequestContext(){
		reset();
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public void sendSignal(IActiveObject target,ISignal s){
		IEventHandler handler = getMetaInfoMap().getEventHandler(s.getUid());
		EventOccurrence occurrence = new EventOccurrence(target, handler);
		getCmtPersistence().persist(occurrence);
		getEventService().scheduleEvent(occurrence);
	}
	@Override
	public UmtPersistence newUmtPersistence(){
		EntityManager result = openHibernateSession();
		((Session) result.getDelegate()).enableFilter("noDeletedObjects");
		return new StandaloneJpaUmtPersistence(result);
	}
	@Override
	public INotificationService getNotificationService(){
		return null;
	}
	@Override
	public ConversationalPersistence createConversationalPersistence(){
		return new StandaloneJpaConversationalPersistence(getEntityManager());
	}
}
