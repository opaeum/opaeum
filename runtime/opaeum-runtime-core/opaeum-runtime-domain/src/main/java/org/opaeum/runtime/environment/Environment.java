package org.opaeum.runtime.environment;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.ExceptionAnalyser;
import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.event.EventService;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.DatabaseManagementSystem;
import org.opaeum.runtime.persistence.UmtPersistence;

public abstract class Environment{
	public static final String HIBERNATE_CONFIG_NAME = "opaeum.hibernate.config.name";
	public static final String JBPM_KNOWLEDGE_BASE_IMPLEMENTATION = "opaeum.jbpm.knowledgebase.implementation";
	public static final String ENVIRONMENT_IMPLEMENTATION = "opaeum.environment.implementation";
	public static final String PROPERTIES_FILE_NAME = "opaeum.env.properties";
	public static final String PERSISTENT_NAME_CLASS_MAP = "opaeum.persistentname.classmap.implementation";
	public static final String DB_USER = "opaeum.database.user";
	public static final String DB_PASSWORD = "opaeum.database.pwd";
	public static final String JDBC_CONNECTION_URL = "opaeum.jdbc.connection.url";
	public static final String DEFAULT_CURRENCY = "opaeum.default.currency";
	public static final String DEFAULT_LOCALE = "opaeum.default.locale";
	public static final String MESSAGE_FILE_PREFIX = "opaeum.message.file.prefix";
	public static final String DBMS = "opaeum.database.management.system";
	public static final String DEV_USERNAME = "opaeum.developer.username";
	public static final String UPDATE_DB_DEF = "opaeum.update.db.definition";
	public static final String DEV_UI_DIR = "opaeum.development.ui.directory";
	protected static final Map<String,Environment> instanceMap = Collections.synchronizedMap(new HashMap<String,Environment>());
	protected static final Map<Class<? extends Environment>,Environment> classInstanceMap = Collections
			.synchronizedMap(new HashMap<Class<? extends Environment>,Environment>());
	protected static final Map<Class<?>,Environment> classDefaultEnvironmentMap = Collections
			.synchronizedMap(new HashMap<Class<?>,Environment>());
	public static final String JDBC_DRIVER_CLASS = "opaeum.jdbc.driver.class";
	private static final String DEFAULT_ENVIRONMENT = "opaeum.default.environment";
	private final EventService EVENT_SERVICE = new EventService(this);
	private long lastRefresh = System.currentTimeMillis();
	protected Properties properties;
	private DatabaseManagementSystem dbms;
	private long refreshInterval = 5000;
	private Locale defaultLocale;
	private ThreadLocal<IAnyValue> currentUser = new ThreadLocal<IAnyValue>();
	private ThreadLocal<IAnyValue> currentRole = new ThreadLocal<IAnyValue>();
	private ThreadLocal<Locale> userPreferredLocal=new ThreadLocal<Locale>();
	static Map<Locale,Properties> messages = new HashMap<Locale,Properties>();
	// static private Map<String,Class<?>> classes = new HashMap<String,Class<?>>();
	private static Map<String,Locale> localeMap;
	static{
		getLocaleMap();
	}
	public abstract JavaMetaInfoMap getMetaInfoMap();
	public abstract void reset();
	public abstract void endRequestContext();
	public abstract void startRequestContext();
	public abstract UmtPersistence createUmtPersistence();
	public abstract ConversationalPersistence createConversationalPersistence();
	public abstract CmtPersistence getCurrentPersistence();
	protected abstract <T>T getComponentImpl(Class<T> clazz,Annotation qualifiers);
	public abstract <T>Class<T> getImplementationClass(T o);
	protected Environment(){
	}
	public void register(){
		instanceMap.put(getApplicationIdentifier(), this);
		classInstanceMap.put(getClass(), this);
	}
	public void unregister(){
		instanceMap.remove(getApplicationIdentifier());
		classInstanceMap.remove(getClass());
	}
	public IAnyValue getCurrentUser(){
		return currentUser.get();
	}
	public IAnyValue getCurrentRole(){
		return currentRole.get();
	}
	public void setCurrentUser(IAnyValue currentUser){
		this.currentUser.set(currentUser);
	}
	public void setCurrentRole(IAnyValue currentRole){
		this.currentRole.set(currentRole);
	}
	public void putProperty(String name,String value){
		properties.put(name, value);
	}
	public DatabaseManagementSystem getDatabaseManagementSystem(){
		if(this.dbms == null){
			this.dbms = DatabaseManagementSystem.valueOf(getProperty(DBMS));
		}
		return dbms;
	}
	public String getProperty(String...name){
		maybeRefresh();
		if(name.length == 1){
			return properties.getProperty(name[0]);
		}else{
			return properties.getProperty(name[0], name[1]);
		}
	}
	private void maybeRefresh(){
		if(System.currentTimeMillis() - lastRefresh > refreshInterval){
			properties = loadProperties(PROPERTIES_FILE_NAME, getClass());
			messages.clear();
		}
	}
	protected static Properties loadProperties(String propertiesFileName,Class<?> referenceClass){
		Properties properties;
		try{
			properties = new Properties();
			URL resource = referenceClass.getResource("/" + propertiesFileName);
			if(resource == null){
				resource = referenceClass.getResource(propertiesFileName);
			}
			properties.load(resource.openStream());
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return properties;
	}
	public EventService getEventService(){
		return EVENT_SERVICE;
	}
	public <T>T getComponent(Class<T> clazz){
		return getComponentImpl(clazz, null);
	}
	public Currency getDefaultCurrency(){
		return Currency.getInstance(getProperty(DEFAULT_CURRENCY, "ZAR"));
	}
	public String getMessage(String string,Object...args){
		maybeRefresh();
		Locale l = Locale.getDefault();
		if(getUserPreferredLocale() != null){
			l = getUserPreferredLocale();
		}else if(getDefaultLocale() != null){
			l = getDefaultLocale();
		}
		Properties messagesForLocale = messages.get(l);
		if(messagesForLocale == null){
			try{
				messagesForLocale = loadProperties(getMessageFileName(l), getClass());
				messages.put(l, messagesForLocale);
			}catch(Exception e){
				return getDefaultMessage(string);
			}
		}
		String message = messagesForLocale.getProperty(string);
		if(message == null){
			return getDefaultMessage(string);
		}else{
			return MessageFormat.format(message, args);
		}
	}
	public Locale getUserPreferredLocale(){
		return userPreferredLocal.get();
	}
	private String getDefaultMessage(String string){
		String[] split = string.split("\\:\\:");
		return NameConverter.capitalize(NameConverter.separateWords(split[split.length - 1]));
	}
	public static String getMessageFileName(Locale l){
		return MESSAGE_FILE_PREFIX + l.toString();
	}
	public Locale getDefaultLocale(){
		if(this.defaultLocale == null){
			String s = getProperty(DEFAULT_LOCALE);
			this.defaultLocale = getLocale(s);
		}
		return this.defaultLocale;
	}
	public static java.util.List<Locale> getAvailableLocales(){
		java.util.List<Locale> availableLocales = new ArrayList<Locale>(Arrays.asList(Locale.getAvailableLocales()));
		availableLocales.add(new Locale("af", "za"));
		availableLocales.add(new Locale("zu", "za"));
		availableLocales.add(new Locale("xh", "za"));
		availableLocales.add(new Locale("nso", "za"));
		Collections.sort(availableLocales, new Comparator<Locale>(){
			@Override
			public int compare(Locale o1,Locale o2){
				int c = o1.getDisplayCountry().compareTo(o2.getDisplayCountry());
				if(c == 0){
					return o1.getDisplayLanguage().compareTo(o2.getDisplayLanguage());
				}
				return c;
			}
		});
		return availableLocales;
	}
	public static Locale getLocale(String preferredLocale){
		return getLocaleMap().get(preferredLocale);
	}
	private static Map<String,Locale> getLocaleMap(){
		if(localeMap == null){
			localeMap = new HashMap<String,Locale>();
			for(Locale locale:getAvailableLocales()){
				localeMap.put(locale.toString(), locale);
			}
		}
		return localeMap;
	}
	public static Environment buildInstanceForRelease(String applicationId,VersionNumber from){
		// TODO fix this-maygenerate an Environmnet per version
		// TODO Auto-generated method stub
		String key = applicationId + from.toVersionString();
		return getEnvironment(key);
	}
	public static Environment getEnvironment(String key){
		return instanceMap.get(key);
	}
	public static Environment getDefaultEnvironment(Class<?> referenceClass){
		Environment result = classDefaultEnvironmentMap.get(referenceClass);
		if(result == null){
			Properties props = loadProperties(Environment.PROPERTIES_FILE_NAME, referenceClass);
			String defaultEnv = props.getProperty(DEFAULT_ENVIRONMENT);
			result=Environment.getEnvironment(defaultEnv);
			if(result==null){
				//Maybe a classname
				try{
					result= getEnvironment((Class<? extends Environment>)IntrospectionUtil.classForName(defaultEnv));
				}catch(Exception e){
					
				}
			}
			classDefaultEnvironmentMap.put(referenceClass, result);
		}
		return result;
	}
	public static Environment getEnvironment(Class<? extends Environment> clss){
		Environment result = instanceMap.get(clss);
		if(result == null){
			try{
				Field instance = clss.getDeclaredField("INSTANCE");
				instance.setAccessible(true);
				result = (Environment) instance.get(null);
			}catch(Exception e){
				new ExceptionAnalyser(e).throwRootCause();
			}
			result.register();
		}
		return result;
	}
	public abstract String getApplicationIdentifier();
}
