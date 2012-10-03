package org.opaeum.runtime.environment;

import java.io.IOException;
import java.lang.annotation.Annotation;
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
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.event.EventService;
import org.opaeum.runtime.organization.IParticipantBase;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
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
	private static final EventService EVENT_SERVICE = new EventService();
	private long lastRefresh = System.currentTimeMillis();
	protected static ThreadLocal<Environment> instance = new ThreadLocal<Environment>();
	protected JavaMetaInfoMap metaInfoMap;
	protected Properties properties;
	protected static Class<? extends Environment> defaultImplementation;
	private DatabaseManagementSystem dbms;
	private IPersonNode currentUser;
	private IParticipantBase currentRole;
	private AbstractPersistence currentPersistence;
	private long refreshInterval = 5000;
	private Locale defaultLocale;
	static Map<Locale,Properties> messages = new HashMap<Locale,Properties>();
	static private Map<String,Class<?>> classes = new HashMap<String,Class<?>>();
	private static Map<String,Locale> localeMap;
	static{
		getLocaleMap();
	}
	public static Environment buildInstanceForRelease(VersionNumber n){
		Environment env = (Environment) instantiateImplementation(ENVIRONMENT_IMPLEMENTATION, loadProperties(propertiesFileName(n)));
		return env;
	}
	public void putProperty(String name,String value){
		properties.put(name, value);
	}
	private static String propertiesFileName(VersionNumber n){
		return "opaeum.env" + n.getSuffix() + ".properties";
	}
	public static Environment getInstance(){
		if(instance.get() == null){
			if(defaultImplementation == null){
				instance.set((Environment) instantiateImplementation(ENVIRONMENT_IMPLEMENTATION));
			}else{
				Environment newInstance = IntrospectionUtil.newInstance(defaultImplementation);
				newInstance.properties = loadProperties();
				instance.set(newInstance);
			}
		}
		return instance.get();
	}
	public JavaMetaInfoMap getMetaInfoMap(){
		if(metaInfoMap == null){
			metaInfoMap = (JavaMetaInfoMap) instantiateImplementation(PERSISTENT_NAME_CLASS_MAP);
		}
		return metaInfoMap;
	}
	public abstract <T>Class<T> getImplementationClass(T o);
	public static Object instantiateImplementation(String environmentImplementation){
		return instantiateImplementation(environmentImplementation, loadProperties());
	}
	public void registerClass(Class<?> c){
		classes.put(c.getName(), c);
	}
	private static Object instantiateImplementation(String environmentImplementation,Properties properties){
		Object newInstance;
		Class<? extends Object> cls;
		String className = properties.getProperty(environmentImplementation);
		if(classes.containsKey(className)){
			cls = classes.get(className);
		}else{
			cls = IntrospectionUtil.classForName(className);
		}
		newInstance = IntrospectionUtil.newInstance(cls);
		if(newInstance instanceof Environment){
			((Environment) newInstance).properties = properties;
		}
		return newInstance;
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
			properties = loadProperties();
			messages.clear();
		}
	}
	public static Properties loadProperties(){
		String propertiesFileName = PROPERTIES_FILE_NAME;
		return loadProperties(propertiesFileName);
	}
	private static Properties loadProperties(String propertiesFileName){
		Properties properties;
		try{
			properties = new Properties();
			URL resource = Thread.currentThread().getContextClassLoader().getResource("/" + propertiesFileName);
			if(resource == null){
				resource = Thread.currentThread().getContextClassLoader().getResource(propertiesFileName);
			}
			if(resource == null && defaultImplementation != null){
				resource = defaultImplementation.getResource(propertiesFileName);
			}
			if(resource == null && classes.size() > 0){
				resource = classes.values().iterator().next().getResource(propertiesFileName);
			}
			properties.load(resource.openStream());
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return properties;
	}
	public IPersonNode getCurrentUser(){
		return currentUser;
	}
	public IParticipantBase getCurrentRole(){
		return currentRole;
	}
	public void setCurrentUser(IPersonNode currentUser){
		this.currentUser = currentUser;
	}
	public void setCurrentRole(IParticipantBase currentRole){
		this.currentRole = currentRole;
	}
	public abstract <T>T getComponent(Class<T> clazz);
	public abstract <T>T getComponent(Class<T> clazz,Annotation qualifiers);
	public abstract void reset();
	public abstract void endRequestContext();
	public abstract void startRequestContext();
	public EventService getEventService(){
		return EVENT_SERVICE;
	}
	public abstract void sendSignal(IActiveObject target,ISignal s);
	public abstract UmtPersistence newUmtPersistence();
	public abstract ConversationalPersistence createConversationalPersistence();
	public abstract UmtPersistence getUmtPersistence();
	public AbstractPersistence getCurrentPersistence(){
		return currentPersistence;
	}
	public void setCurrentPersistence(AbstractPersistence currentPersistence){
		this.currentPersistence = currentPersistence;
	}
	public Currency getDefaultCurrency(){
		return Currency.getInstance(getProperty(DEFAULT_CURRENCY, "ZAR"));
	}
	public String getMessage(String string,Object...args){
		maybeRefresh();
		Locale l = Locale.getDefault();
		if(getCurrentUser().getPreferredLocale() != null){
			l = getCurrentUser().getPreferredLocale();
		}else if(getDefaultLocale() != null){
			l = getDefaultLocale();
		}
		Properties messagesForLocale = messages.get(l);
		if(messagesForLocale == null){
			try{
				messagesForLocale = loadProperties(getMessageFileName(l));
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
}
