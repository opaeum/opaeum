package org.opaeum.runtime.jpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.Session;
import org.hibernate.annotations.Where;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class AbstractJpaEnvironment extends Environment{
	Map<Class<?>,Object> components = new HashMap<Class<?>,Object>();
	protected HibernateEntityManagerFactory entityManagerFactory;
	private Boolean isInJee;
	private ThreadLocal<JpaCmtPersistence> currentPersistence = new ThreadLocal<JpaCmtPersistence>();
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
	private HibernateEntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory == null){
			loadDriver("org.hsqldb.jdbcDriver");
			loadDriver("org.postgresql.Driver");
			loadDriver("oracle.jdbc.driver.OracleDriver");
			loadDriver("com.ibm.db2.jcc.DB2Driver");
			loadDriver("org.gjt.mm.mysql.Driver");
			
			ClassLoader oldCcl = Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			boolean issueDdl = "true".equals(getProperty(UPDATE_DB_DEF));
			Set<String[]> foreignKeys=new HashSet<String[]>();
			if(issueDdl){
				createSchemas(foreignKeys);
			}
			// HibernatePersistence hp = new HibernatePersistence();
			Ejb3Configuration cfg = new Ejb3Configuration();
			Ejb3Configuration configured = cfg.configure(getPersistenceUnitInfo(), getPersistenceUnitInfo().getProperties());
			Configuration hibernateConfiguration = configured.getHibernateConfiguration();
			if(issueDdl){
				Dialect dialect = Dialect.getDialect(getPersistenceUnitInfo().getProperties());
				Connection connection = null;
				try{
					connection = getUnmanagedConnection();
					connection.setAutoCommit(true);
					String[] sqlStrings = hibernateConfiguration.generateSchemaUpdateScript(dialect, new DatabaseMetadata(connection, dialect));
					Statement st = connection.createStatement();
					for(String sql:sqlStrings){
						System.out.println(sql);
						try{
							if(shouldIssueSql(foreignKeys, sql)){
							st.executeUpdate(sql);
							}
						}catch(SQLException e){
							System.out.println(e);
						}
					}
				}catch(SQLException e){
					System.out.println(e);
				}finally{
					if(connection != null){
						try{
							connection.close();
						}catch(SQLException e){
						}
					}
				}
			}
			entityManagerFactory = (EntityManagerFactoryImpl) configured.buildEntityManagerFactory();
//			Thread.currentThread().setContextClassLoader(oldCcl);
		}
		return entityManagerFactory;
	}
	private boolean shouldIssueSql(Set<String[]> foreignKeys,String sql){
		boolean shouldCreateForeignKey=true;
		String upperCase = sql.toUpperCase();
		if(upperCase.contains("FOREIGN KEY")){
			for(String[] s:foreignKeys){
				if(upperCase.contains(s[0].toUpperCase()) && upperCase.contains(s[1].toUpperCase())){
					shouldCreateForeignKey=false;
					break;
				}
			}
		}
		return shouldCreateForeignKey;
	}
	private void createSchemas(Set<String[]> foreignKeys){
		Set<String> schemas = new HashSet<String>();
		for(Class<?> class1:getMetaInfoMap().getAllClasses()){
			if(class1.isAnnotationPresent(Table.class)){
				schemas.add(class1.getAnnotation(Table.class).schema());
				for(Field field:class1.getDeclaredFields()){
					if(field.isAnnotationPresent(Where.class) && field.isAnnotationPresent(JoinColumn.class)){
						Class<?> targetClass = getTargetClass(field);
						if(targetClass.isAnnotationPresent(Table.class)){
							foreignKeys.add(new String[]{field.getAnnotation(JoinColumn.class).name(), targetClass.getAnnotation(Table.class).name()});
						}
					}
				}
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
			connection.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
	private Class<?> getTargetClass(Field field){
		if(field.isAnnotationPresent(OneToMany.class)){
			return field.getAnnotation(OneToMany.class).targetEntity();
		}else if(field.isAnnotationPresent(ManyToMany.class)){
			return field.getAnnotation(ManyToMany.class).targetEntity();
		}else{
			return field.getType();
		}
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
			// TODO etc
			Connection connection = DriverManager.getConnection(super.getProperty(JDBC_CONNECTION_URL), getProperty(Environment.DB_USER, "sa"),
					getProperty(Environment.DB_PASSWORD, ""));
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
			Driver d= (Driver) classLoader.loadClass(driver).newInstance();
			DriverManager.registerDriver(d);
		}catch(Exception e1){
				System.out.println(e1);
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
		return new JpaUmtPersistence(result, this);
	}
	protected boolean isInJee(){
		if(isInJee == null){
			try{
				new InitialContext().lookup("java:comp");
				isInJee = true;
			}catch(Throwable e){
				isInJee = false;
				System.out.println("AbstractJpaEnvironment.isInJee()");
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
}
