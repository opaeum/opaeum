package org.opaeum.runtime.jpa;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class AbstractJpaEnvironment extends Environment{
	Map<Class<?>,Object> components = new HashMap<Class<?>,Object>();
	protected HibernateEntityManagerFactory entityManagerFactory;
	private Boolean isInJee;
	private ThreadLocal<JpaCmtPersistence> currentPersistence = new ThreadLocal<JpaCmtPersistence>();
	private boolean issueDdl = false;
	protected abstract PersistenceUnitInfo getPersistenceUnitInfo();
	// protected abstract Map getPersistenceUnitProperties();
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	public HibernateEntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory == null){
			loadDriver("org.hsqldb.jdbcDriver");
			loadDriver("org.postgresql.Driver");
			loadDriver("oracle.jdbc.driver.OracleDriver");
			loadDriver("com.ibm.db2.jcc.DB2Driver");
			loadDriver("org.gjt.mm.mysql.Driver");
			ClassLoader oldCcl = Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			boolean issueDdl = shouldIssueDdl();
			entityManagerFactory = new DatabasePreparer(this).prepareDatabase(issueDdl);
			// Thread.currentThread().setContextClassLoader(oldCcl);
		}
		return entityManagerFactory;
	}
	protected Connection getConnection() throws SQLException{
		if(isInJee()){
			return getPersistenceUnitInfo().getJtaDataSource().getConnection();
		}else{
			return getUnmanagedConnection();
		}
	}
	protected Connection getUnmanagedConnection() throws SQLException{
		if(isInJee()){
			return getPersistenceUnitInfo().getNonJtaDataSource().getConnection();
		}else{
			Connection connection = DriverManager.getConnection(super.getDbConnectionUrl(), getDbUser(),
					getDbPassword());
			return connection;
		}
	}
	private void loadDriver(String driver){
		loadDriver(driver, Session.class.getClassLoader());
		loadDriver(driver, AbstractJpaEnvironment.class.getClassLoader());
		loadDriver(driver, getClass().getClassLoader());
		loadDriver(driver, Thread.currentThread().getContextClassLoader());
	}
	private void loadDriver(String driver,ClassLoader classLoader){
		try{
			Driver d = (Driver) classLoader.loadClass(driver).newInstance();
			DriverManager.registerDriver(d);
		}catch(Exception e1){
			System.out.println(e1);
		}
	}
	private EntityManager openEntityManager(){
		EntityManager em = getEntityManagerFactory().createEntityManager();
		return em;
	}
	@Override
	public <T>T getComponentImpl(Class<T> clazz,Annotation qualifiers){
		return getComponent(clazz);
	}
	@Override
	public void reset(){
	}
	@Override
	public void endRequestContext(){
		reset();
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public UmtPersistence createUmtPersistence(){
		EntityManager result = openEntityManager();
		return new JpaUmtPersistence(result, this);
	}
	protected boolean isInJee(){
		if(isInJee == null){
			try{
				new InitialContext().lookup("java:comp");
				isInJee = true;
				System.out.println("AbstractJpaEnvironment.isInJee()=true");
			}catch(Throwable e){
				isInJee = false;
				System.out.println("AbstractJpaEnvironment.isInJee()=false");
			}
		}
		return isInJee;
	}
	@Override
	public ConversationalPersistence createConversationalPersistence(){
		EntityManager result = openEntityManager();
		return new JpaConversationalPersistence(result, this);
	}
	@Override
	public CmtPersistence getCurrentPersistence(){
		if(currentPersistence.get() == null){
			currentPersistence.set(new JpaCmtPersistence(getEntityManagerFactory().getSessionFactory(), this, currentPersistence));
		}
		return currentPersistence.get();
	}
	public boolean shouldIssueDdl(){
		return issueDdl || "true".equals(getProperty(UPDATE_DB_DEF));
	}
	public void setIssueDdl(boolean issueDdl){
		this.issueDdl = issueDdl;
	}
}
