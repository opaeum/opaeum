package org.opaeum.runtime.jpa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
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
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabasePreparer{
	Logger logger = LoggerFactory.getLogger(getClass());
	private AbstractJpaEnvironment env;
	private DdlFilter ddlFilter;
	private File extraDdlDir;
	public DatabasePreparer(AbstractJpaEnvironment env){
		super();
		this.env = env;
		String f = env.getProperty("opaeum.ddl.filter", DefaultDdlFilter.class.getName());
		try{
			ddlFilter = IntrospectionUtil.newInstance(f);
		}catch(Exception e){
			ddlFilter = new DefaultDdlFilter();
		}
		try{
			extraDdlDir = new File(env.getProperty("opaeum.extra.ddl.dir"));
		}catch(Exception e){
		}
	}
	public PersistenceUnitInfo getPersistenceUnitInfo(){
		return env.getPersistenceUnitInfo();
	}
	public boolean shouldIssueSql(Set<String[]> foreignKeys,String sql){
		boolean shouldCreateForeignKey = true;
		String upperCase = sql.toUpperCase();
		if(upperCase.contains("FOREIGN KEY")){
			if(!ddlFilter.shouldIssueForeignKey(upperCase)){
				shouldCreateForeignKey = false;
			}else{
				for(String[] s:foreignKeys){
					if(upperCase.contains(s[0].toUpperCase()) && upperCase.contains(s[1].toUpperCase())){
						shouldCreateForeignKey = false;
						break;
					}
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
		EntityManagerFactoryImpl emf;
		try{
			emf = (EntityManagerFactoryImpl) configured.buildEntityManagerFactory();
		}catch(Throwable e){
			// We seem to be getting a funny exception/error here sometimes, maybe OutOfMemory h
			// TODO Auto-generated catch block
			logger.error("Could not build EntityManagerFactory", e);
			throw new RuntimeException(e);
		}
		if(issueDdl){
			ensurePresenceOfIndices(emf);
			issueExtraDdl();
		}
		return emf;
	}
	private void issueExtraDdl(){
		if(extraDdlDir != null && extraDdlDir.exists() && extraDdlDir.isDirectory()){
			Connection connection = null;
			File[] files = extraDdlDir.listFiles(new FileFilter(){
				@Override
				public boolean accept(File arg0){
					return arg0.isFile() && arg0.getName().endsWith(".ddl");
				}
			});
			for(File file:files){
				try{
					connection = getUnmanagedConnection();
					BufferedReader reader = new BufferedReader(new FileReader(file));
					StringBuilder sb = new StringBuilder();
					String line=null;
					while((line=reader.readLine())!=null){
						sb.append(line);
					}
					reader.close();
					issueSql(connection, sb.toString());
				}catch(IOException e){
				}catch(SQLException e){
					log(e);
				}finally{
					closeConnection(connection);
				}
			}
		}
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
					connection = createIndices(connection, mappedClass, t.name(), t.schema());
				}
			}
		}catch(SQLException e){
			log(e);
		}finally{
			closeConnection(connection);
		}
	}
	private Connection createIndices(Connection connection,Class<?> mappedClass,String tableName,String schema) throws SQLException{
		Table superTable = mappedClass.getSuperclass().getAnnotation(Table.class);
		if(superTable != null){
			connection = createIndices(connection, mappedClass.getSuperclass(), superTable.name(), superTable.schema());
		}
		for(Field field:mappedClass.getDeclaredFields()){
			Index annotation = field.getAnnotation(Index.class);
			if(annotation != null){
				connection = createIndex(connection, tableName, schema, annotation);
			}
		}
		org.hibernate.annotations.Table hibernateTable = mappedClass.getAnnotation(org.hibernate.annotations.Table.class);
		if(hibernateTable != null){
			Index[] indexes = hibernateTable.indexes();
			for(Index index:indexes){
				createIndex(connection, tableName, schema, index);
			}
		}
		return connection;
	}
	private Connection createIndex(Connection connection,String tableName,String schema,Index annotation) throws SQLException{
		String[] columnNames = annotation.columnNames();
		StringBuilder sb = new StringBuilder("create index ");
		sb.append(annotation.name());
		sb.append(" on ");
		if(!schema.isEmpty()){
			sb.append(schema);
			sb.append(".");
		}
		sb.append('"');
		sb.append(tableName.replaceAll("`", ""));
		sb.append('"');
		sb.append("(");
		for(int i = 0;i < columnNames.length;i++){
			if(i > 0){
				sb.append(',');
			}
			sb.append('"');
			sb.append(columnNames[i]);
			sb.append('"');
		}
		sb.append(")");
		if(ddlFilter.shouldIssueIndex(sb.toString().toUpperCase())){
			connection = issueSql(connection, sb.toString());
		}
		return connection;
	}
	private Connection getUnmanagedConnection() throws SQLException{
		Connection result = this.env.getUnmanagedConnection();
		result.setAutoCommit(true);// Just to make sure
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
		logger.error(e.getClass().getName() + ":" + e.toString());
	}
	private void log(String sql){
		logger.info(sql);
	}
}
