package org.opaeum.runtime.jpa;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.EntityMode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Where;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabasePreparer{
	Logger logger = LoggerFactory.getLogger(getClass());
	private AbstractJpaEnvironment env;
	public DatabasePreparer(AbstractJpaEnvironment env){
		super();
		this.env = env;
	}
	public PersistenceUnitInfo getPersistenceUnitInfo(){
		return env.getPersistenceUnitInfo();
	}
	public boolean shouldIssueSql(Set<String[]> foreignKeys,String sql){
		boolean shouldCreateForeignKey = true;
		String upperCase = sql.toUpperCase();
		if(upperCase.contains("FOREIGN KEY")){
			for(String[] s:foreignKeys){
				if(upperCase.contains(s[0].toUpperCase()) && upperCase.contains(s[1].toUpperCase()) || upperCase.contains("WORKSPACE_ELEMENT")){
					shouldCreateForeignKey = false;
					break;
				}
			}
		}
		return shouldCreateForeignKey;
	}
	public EntityManagerFactoryImpl prepareDatabase(boolean issueDdl){
		Set<String[]> foreignKeys = new HashSet<String[]>();
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
				for(String sql:sqlStrings){
					if(shouldIssueSql(foreignKeys, sql)){
						connection = issueSql(connection, sql);
					}
				}
			}catch(SQLException e){
				log(e);
			}finally{
				closeConnection(connection);
			}
		}
		EntityManagerFactoryImpl emf = (EntityManagerFactoryImpl) configured.buildEntityManagerFactory();
		if(issueDdl){
			ensurePresenceOfIndices(emf);
		}
		return emf;
	}
	private void closeConnection(Connection connection){
		if(connection != null){
			try{
				connection.close();
			}catch(SQLException e){
			}
		}
	}
	private Connection issueSql(Connection connection,String sql) throws SQLException{
		Statement st = connection.createStatement();
		try{
			st.executeUpdate(sql);
			log(sql);
		}catch(SQLException e){
			connection.close();
			connection = getUnmanagedConnection();
			log("DDL execution failed: " + sql);
			log(e);
		}finally{
			st.close();
		}
		return connection;
	}
	private void ensurePresenceOfIndices(EntityManagerFactoryImpl emf){
		Collection<ClassMetadata> values = emf.getSessionFactory().getAllClassMetadata().values();
		Connection connection = null;
		try{
			connection = getUnmanagedConnection();
			for(ClassMetadata classMetadata:values){
				Class<?> mappedClass = classMetadata.getMappedClass(EntityMode.POJO);
				Table t = mappedClass.getAnnotation(Table.class);
				if(t != null){
					for(Field field:mappedClass.getDeclaredFields()){
						Index annotation = field.getAnnotation(Index.class);
						if(annotation != null){
							String[] columnNames = annotation.columnNames();
							StringBuilder sb = new StringBuilder("create index ");
							sb.append(annotation.name());
							sb.append(" on ");
							if(!t.schema().isEmpty()){
								sb.append(t.schema());
								sb.append(".");
							}
							sb.append(t.name().replaceAll("`", ""));
							sb.append("(");
							for(int i = 0;i < columnNames.length;i++){
								if(i > 0){
									sb.append(',');
								}
								sb.append(columnNames[i]);
							}
							sb.append(")");
							connection = issueSql(connection, sb.toString());
						}
					}
				}
			}
		}catch(SQLException e){
			log(e);
		}finally{
			closeConnection(connection);
		}
	}
	private Connection getUnmanagedConnection() throws SQLException{
		Connection result = this.env.getUnmanagedConnection();
		result.setAutoCommit(true);//Just to make sure
		return result;
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
	public JavaMetaInfoMap getMetaInfoMap(){
		return env.getMetaInfoMap();
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
							foreignKeys.add(new String[]{field.getAnnotation(JoinColumn.class).name(),targetClass.getAnnotation(Table.class).name()});
						}
					}
				}
			}
		}
		schemas.remove(null);
		schemas.remove("");
		Connection connection = null;
		try{
			connection = getUnmanagedConnection();
			for(String string:schemas){
				connection = issueSql(connection, "CREATE SCHEMA " + string + " AUTHORIZATION " + this.env.getDbUser());
				// TODO make this db-independent
			}
		}catch(SQLException e){
			log(e);
		}finally{
			closeConnection(connection);
		}
	}
	private void log(SQLException e){
		logger.error(e.getClass().getName() + ":"+ e.toString());
	}
	private void log(String sql){
		logger.info(sql);
	}
}
