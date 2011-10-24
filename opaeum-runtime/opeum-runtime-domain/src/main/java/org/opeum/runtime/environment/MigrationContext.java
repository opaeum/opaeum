package org.opeum.runtime.environment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.UUID;

import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.persistence.UmtPersistence;

public class MigrationContext{
	private Map<Class<? extends IPersistentObject>,IMigrator> migrators = new HashMap<Class<? extends IPersistentObject>,IMigrator>();
	private Stack<UmtPersistence> fromPersistenceStack = new Stack<UmtPersistence>();
	private Stack<UmtPersistence> toPersistenceStack = new Stack<UmtPersistence>();
	private Map<String,ObjectIdentifier> objectIdentifiers = new HashMap<String,MigrationContext.ObjectIdentifier>();
	public static String FROM_URL="opaeum.migration.from.jdbc.url";
	public static String TO_URL="opaeum.migration.to.jdbc.url";
	public static String FROM_USER="opaeum.migration.from.jdbc.user";
	public static String TO_USER="opaeum.migration.to.jdbc.user";
	public static String FROM_PASSWORD="opaeum.migration.from.jdbc.pwd";
	public static String TO_PASSWORD="opaeum.migration.to.jdbc.pwd";
	private Environment fromEnvironment;
	private Environment toEnvironment;
	private static class ObjectIdentifier{
		Class<?> type;
		long id;
		private ObjectIdentifier(Class<?> type,Long id){
			super();
			this.type = type;
			this.id = id;
		}
	}
	public MigrationContext(VersionNumber from,VersionNumber to,String configFileName) throws FileNotFoundException{
		this.fromEnvironment = Environment.buildInstanceForRelease(from);
		this.toEnvironment = Environment.buildInstanceForRelease(to);
		Properties props = new Properties();
		try{
			props.load(new FileReader(configFileName));
		}catch(IOException e){
			try{
				loadResource(configFileName, props);
			}catch(Exception e2){
				try{
					loadResource("migration.properties", props);
				}catch(Exception e3){
					throw new FileNotFoundException(configFileName);
				}
			}
		}
		fromEnvironment.putProperty(Environment.DB_USER,props.getProperty(FROM_USER));
		fromEnvironment.putProperty(Environment.DB_PASSWORD,props.getProperty(FROM_PASSWORD));
		fromEnvironment.putProperty(Environment.JDBC_CONNECTION_URL,props.getProperty(FROM_URL));
		fromEnvironment.putProperty(Environment.DB_USER,props.getProperty(FROM_USER));
		fromEnvironment.putProperty(Environment.DB_PASSWORD,props.getProperty(FROM_PASSWORD));
		fromEnvironment.putProperty(Environment.JDBC_CONNECTION_URL,props.getProperty(FROM_URL));
	}
	private void loadResource(String configFileName,Properties props) throws FileNotFoundException,IOException{
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
		if(resourceAsStream==null){
			resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/"+configFileName);
		}
		if(resourceAsStream==null){
			throw new FileNotFoundException(configFileName);
		}else{
			props.load(resourceAsStream);
		}
	}
	public void registerMigrator(Class<? extends IPersistentObject> cls,IMigrator migrator){
		migrators.put(cls, migrator);
	}
	@SuppressWarnings("unchecked")
	public <T extends IMigrator>T getMigratorFor(IPersistentObject po){
		Class<IPersistentObject> cls = IntrospectionUtil.getOriginalClass(po);
		T object = (T) migrators.get(cls);
		return object;
	}
	@SuppressWarnings("unchecked")
	public <T>T reloadFromObject(IPersistentObject po){
		return (T) fromPersistenceStack.peek().find(IntrospectionUtil.getOriginalClass(po), po.getId());
	}
	@SuppressWarnings("unchecked")
	public <T>T reloadToObject(IPersistentObject po){
		return (T) toPersistenceStack.peek().find(IntrospectionUtil.getOriginalClass(po), po.getId());
	}
	public static void main(String[] args){
		Map<String,ObjectIdentifier> objectIdentifiers = new HashMap<String,MigrationContext.ObjectIdentifier>();
		for(int i = 0;i < 10000000;i++){
			if(i % 100000 == 0){
				System.out.println(i);
			}
			objectIdentifiers.put(UUID.randomUUID().toString(), new ObjectIdentifier(String.class, (long) i));
		}
		System.out.println(Runtime.getRuntime().totalMemory());
	}
	public void persistToObject(IPersistentObject po){
		toPersistenceStack.peek().persist(po);
		objectIdentifiers.put(po.getUid(), new ObjectIdentifier(IntrospectionUtil.getOriginalClass(po), po.getId()));
	}
	@SuppressWarnings("unchecked")
	public <T>T resolveByUuid(String uuid){
		ObjectIdentifier objectIdentifier = objectIdentifiers.get(uuid);
		if(objectIdentifier == null){
			throw new IllegalStateException("Object with uuid \"" + uuid + "\" has not been stored yet");
		}else{
			return (T) toPersistenceStack.peek().find(objectIdentifier.type, objectIdentifier.id);
		}
	}
	public void push(){
		toPersistenceStack.push(toEnvironment.newUmtPersistence());
		fromPersistenceStack.push(fromEnvironment.newUmtPersistence());
	}
	public void pop(){
		toPersistenceStack.pop();
		fromPersistenceStack.pop();
	}
}
