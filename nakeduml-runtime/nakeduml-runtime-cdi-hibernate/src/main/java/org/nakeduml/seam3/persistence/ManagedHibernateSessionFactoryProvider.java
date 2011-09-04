package org.nakeduml.seam3.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.mapping.PersistentClass;
import org.nakeduml.runtime.environment.Environment;

@ApplicationScoped
public class ManagedHibernateSessionFactoryProvider{
	private SessionFactoryImpl sessionFactory;
	@Produces
	@ApplicationScoped
	public SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			Configuration config = buildConfiguration();
			try{
				ConnectionProvider connProvider = ConnectionProviderFactory.newConnectionProvider(config.getProperties());
				Connection connection = connProvider.getConnection();
				createSchemas(config, connection);
				// TODO first check if it is indeed Postgres
//				config.getTypeResolver().registerTypeOverride(PostgresDialect.PostgresqlMateralizedBlobType.INSTANCE);
				this.sessionFactory = (SessionFactoryImpl) config.buildSessionFactory();
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return this.sessionFactory;
	}
	public static Configuration buildConfiguration(){
		Configuration config = new Configuration();
		config.configure(Environment.getInstance().getProperty(Environment.HIBERNATE_CONFIG_NAME));
		return config;
	}
	public static void createSchemas(Configuration config,Connection connection) throws SQLException{
		config.buildMappings();
		Iterator<PersistentClass> classMappings = config.getClassMappings();
		Set<String> schemas = new HashSet<String>();
		while(classMappings.hasNext()){
			PersistentClass persistentClass = (PersistentClass) classMappings.next();
			String schema = persistentClass.getTable().getSchema();
			if(schema != null){
				schemas.add(schema.replaceAll("`", ""));
			}
		}
		Statement st = connection.createStatement();
		for(String string:schemas){
			try{
				st.executeUpdate("CREATE SCHEMA " + string + " AUTHORIZATION " + Environment.getInstance().getProperty(Environment.DB_USER));
				connection.commit();
			}catch(Exception e){
			}
		}
	}
	@Dependent
	@Produces
	@UserManagedSession
	public Session getSession(){
		return this.sessionFactory.openSession();
	}
	@Dependent
	@Produces
	public Session getTransactionScopedSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@PreDestroy
	public void close(){
		this.sessionFactory.close();
	}
}
