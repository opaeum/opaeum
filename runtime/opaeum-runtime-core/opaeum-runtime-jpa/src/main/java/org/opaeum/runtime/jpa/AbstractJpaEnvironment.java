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
import javax.persistence.Table;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.opaeum.hibernate.domain.HibernateCmtPersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class AbstractJpaEnvironment extends Environment{
	Map<Class<?>,Object> components = new HashMap<Class<?>,Object>();
	protected HibernateEntityManagerFactory entityManagerFactory;
	private Boolean isInJee;
	protected abstract PersistenceUnitInfo getPersistenceUnitInfo();
	// protected abstract Map getPersistenceUnitProperties();
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	public AbstractJpaEnvironment(){
		properties = loadProperties(PROPERTIES_FILE_NAME, getClass());
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
				Connection connection = getUnmanagedConnection();
				connection.setAutoCommit(true);
				Statement st = connection.createStatement();
				for(String string:schemas){
					try{
						st.executeUpdate("CREATE SCHEMA " + string + " AUTHORIZATION " + getProperty(DB_USER));
						// TODO make this db-independent
					}catch(Exception e){
						System.out.println(e);
						// e.printStackTrace();
					}
				}
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			// HibernatePersistence hp = new HibernatePersistence();
			Ejb3Configuration cfg = new Ejb3Configuration();
			Ejb3Configuration configured = cfg.configure(getPersistenceUnitInfo(), getPersistenceUnitInfo().getProperties());
			Configuration hibernateConfiguration = configured.getHibernateConfiguration();
			Dialect dialect = Dialect.getDialect(getPersistenceUnitInfo().getProperties());
			try{
				Connection connection = getUnmanagedConnection();
				connection.setAutoCommit(true);
				String[] sqlStrings = hibernateConfiguration.generateSchemaUpdateScript(dialect, new DatabaseMetadata(connection, dialect));
				Statement st = connection.createStatement();
				for(String sql:sqlStrings){
					System.out.println(sql);
					try{
						st.executeUpdate(sql);
					}catch(SQLException e){
						e.printStackTrace();
						System.out.println(e);
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println(e);
			}
			entityManagerFactory = (EntityManagerFactoryImpl) configured.buildEntityManagerFactory();
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
			loadDriver("org.hsqldb.jdbcDriver");
			loadDriver("org.postgres.Driver");
			loadDriver("oracle.jdbc.driver.OracleDriver");
			loadDriver("com.ibm.db2.jcc.DB2Driver");
			loadDriver("org.gjt.mm.mysql.Driver");
			// TODO etc
			Connection connection = DriverManager.getConnection(super.getProperty(JDBC_CONNECTION_URL), getProperty(Environment.DB_USER, "sa"),
					getProperty(Environment.DB_PASSWORD, ""));
			return connection;
		}
	}
	private void loadDriver(String driver){
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e1){
		}
	}
	@SuppressWarnings("unchecked")
	private <T>T getComponentImpl(Class<T> clazz){
		if(components.get(clazz.getName()) != null){
			return (T) components.get(clazz.getName());
		}else{
			return (T) components.get(clazz);
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
		((Session) result.getDelegate()).enableFilter("noDeletedObjects");
		return new StandaloneJpaUmtPersistence(result, this);
	}
	protected boolean isInJee(){
		if(isInJee == null){
			try{
				Class.forName("org.opaeum.audit.ChildAuditedObject");// A Test class
				// TODO what about non-eclipse environment?What if opaeum is not a project?
				isInJee = false;
			}catch(ClassNotFoundException e){
				isInJee = true;
				System.out.println("AbstractJpaEnvironment.isInJee()");
			}
		}
		return isInJee;
	}
	@Override
	public ConversationalPersistence createConversationalPersistence(){
		return new JpaConversationalPersistence(openEntityManager(), this);
	}
	@Override
	public CmtPersistence getCurrentPersistence(){
		return new HibernateCmtPersistence(entityManagerFactory.getSessionFactory().getCurrentSession(), this);
	}
}
