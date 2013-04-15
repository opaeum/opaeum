package org.opaeum.runtime.jpa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.ejb.HibernatePersistence;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.runtime.environment.Environment;

public class AbstractPersistenceUnitInfo implements PersistenceUnitInfo{
	AbstractJpaEnvironment env;
	public AbstractPersistenceUnitInfo(AbstractJpaEnvironment env){
		super();
		this.env = env;
	}
	public String getPersistenceProviderClassName(){
		return HibernatePersistence.class.getName();
	}
	public DataSource getJtaDataSource(){
		try{
			return (DataSource) new InitialContext().lookup(getDatasourceUrl());
		}catch(NamingException e){
			return null;
		}
	}
	public DataSource getNonJtaDataSource(){
		try{
			return (DataSource) new InitialContext().lookup(getDatasourceUrl() + "NonXA");
		}catch(NamingException e){
			return null;
		}
	}
	public List<String> getMappingFileNames(){
		return Collections.emptyList();
	}
	public List<URL> getJarFileUrls(){
		return Collections.emptyList();
	}
	public URL getPersistenceUnitRootUrl(){
		return getClass().getResource("/");
	}
	public boolean excludeUnlistedClasses(){
		return true;
	}
	public void addTransformer(ClassTransformer transformer){
	}
	public ClassLoader getNewTempClassLoader(){
		return getClassLoader();
	}
	public String getPersistenceXMLSchemaVersion(){
		return "2.0";
	}
	// public SharedCacheMode getSharedCacheMode(){
	// return SharedCacheMode.NONE;
	// }
	public PersistenceUnitTransactionType getTransactionType(){
		if(env.isInJee()){
			 return PersistenceUnitTransactionType.JTA;
		}
		return PersistenceUnitTransactionType.RESOURCE_LOCAL;
	}
	// public ValidationMode getValidationMode(){
	// return ValidationMode.NONE;
	// }
	public String getPersistenceUnitName(){
		return null;
	}
	public List<String> getManagedClassNames(){
		return null;
	}
	public Properties getProperties(){
		Properties props = getBasicProperties();
		addEventListeners(props);
		configureConnectivityAndTransactioning(props);
		return props;
	}
	protected void configureConnectivityAndTransactioning(Properties props) {
		if(env.isInJee()){
			props.put("connection.datasource", getDatasourceUrl());
			// props.put("hibernate.format_sql","true" );
			props.put("jboss.entity.manager.factory.jndi.name", "java:/" + getPersistenceUnitName() + "DataSource");
			props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
			props.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.JBossTransactionManagerLookup");
			if(isJpa2()){
				props.put("hibernate.current_session_context_class", "org.hibernate.context.internal.JTASessionContext");
			}else{
				props.put("hibernate.current_session_context_class", "org.hibernate.context.JTASessionContext");
			}
		}else{
			if(isJpa2()){
				props.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jdbc.JdbcTransactionFactory");
			}else{
				props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
			}
			props.put("hibernate.connection.driver_class", env.getProperty(Environment.JDBC_DRIVER_CLASS, "org.postgres.Driver"));
			props.put("hibernate.connection.url", env.getDbConnectionUrl());
			props.put("hibernate.connection.username", env.getDbUser());
			props.put("hibernate.connection.password", env.getDbPassword());
		}
	}
	protected void addEventListeners(Properties props) {
		if(isJpa2()){
			props.put("hibernate.ejb.event.post-load", getListenerClass().getName());
			props.put("hibernate.ejb.event.post-insert", getListenerClass().getName());
			props.put("hibernate.ejb.event.post-update", getListenerClass().getName());
			props.put("hibernate.ejb.event.flush-entity", getListenerClass().getName());
			props.put("hibernate.ejb.event.flush", getListenerClass().getName());
			props.put("hibernate.ejb.event.create-onflush", getListenerClass().getName());
			props.put("hibernate.ejb.event.create", getListenerClass().getName());
			props.put("hibernate.ejb.event.pre-update", getListenerClass().getName());
		}else{
			props.put("hibernate.ejb.event.post-load", "org.hibernate.ejb.event.EJB3PostLoadEventListener,"+getListenerClass().getName());
			props.put("hibernate.ejb.event.post-insert", "org.hibernate.ejb.event.EJB3PostInsertEventListener,"+getListenerClass().getName());
			props.put("hibernate.ejb.event.post-update", "org.hibernate.ejb.event.EJB3PostUpdateEventListener,"+getListenerClass().getName());
			props.put("hibernate.ejb.event.pre-update", getListenerClass().getName());
			props.put("hibernate.ejb.event.flush-entity", "org.hibernate.ejb.event.EJB3FlushEntityEventListener,"+getListenerClass().getName());
			props.put("hibernate.ejb.event.flush", getListenerClass().getName());
			props.put("hibernate.ejb.event.create-onflush",
					"org.hibernate.ejb.event.EJB3PersistOnFlushEventListener,"+getListenerClass().getName());
			props.put("hibernate.ejb.event.create", "org.hibernate.ejb.event.EJB3PersistOnFlushEventListener,"+getListenerClass().getName());
		}
	}
	protected Properties getBasicProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", getHibernateDialect());
		props.put("hibernate.cache.use_second_level_cache", "false");
		props.put("hibernate.cache.use_query_cache", "false");
		props.put("hibernate.default_batch_fetch_size", "8");
		props.put("hibernate.order_updates", "true");
		props.put("hibernate.order_inserts", "true");
		props.put("hibernate.jdbc.batch_size", "20");
		props.put("hibernate.max_fetch_depth", "1");
		props.put("hibernate.show_sql", env.getProperty(Environment.SHOW_SQL, "false"));
		return props;
	}
	protected Class<? extends EventDispatcher> getListenerClass() {
		return EventDispatcher.class;
	}
	private boolean isJpa2(){
		try{
			getClass().getClassLoader().loadClass("org.hibernate.engine.transaction.internal.jdbc.JdbcTransactionFactory");
			return true;
		}catch(ClassNotFoundException e){
			return false;
		}
	}
	protected String getDatasourceUrl(){
		return "java:/" + getPersistenceUnitName() + "Datasource";
	}
	protected String getHibernateDialect(){
		switch(env.getDatabaseManagementSystem()){
		case POSTGRESQL:
			return PostgreSQLDialect.class.getName();
		case HSQL:
			return HSQLDialect.class.getName();
		default:
			return HSQLDialect.class.getName();
		}
	}
	public ClassLoader getClassLoader(){
		return getClass().getClassLoader();
	}
	protected Collection readClassNames(String string){
		Set<String> result = new HashSet<String>();
		try{
			InputStream is = getClass().getResourceAsStream("/" + string);
			if(is != null){
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String c = null;
				while((c = br.readLine()) != null){
					result.add(c);
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}
}
