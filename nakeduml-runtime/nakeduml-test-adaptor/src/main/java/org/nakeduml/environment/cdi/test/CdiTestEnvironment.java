package org.nakeduml.environment.cdi.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jboss.seam.solder.literal.DefaultLiteral;
import org.jboss.seam.transaction.literal.DefaultTransactionLiteral;
import org.jboss.weld.manager.BeanManagerImpl;
import org.jboss.weld.mock.MockBeanDeploymentArchive;
import org.jboss.weld.mock.MockDeployment;
import org.jboss.weld.mock.MockServletLifecycle;
import org.jboss.weld.test.BeanManagerLocator;
import org.nakeduml.environment.AbstractJbpmKnowledgeBase;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.hibernate.domain.PostgresDialect;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.test.adaptor.CditTestLogger;

public class CdiTestEnvironment extends Environment{
	private MockTimeEventDispatcher timeEventDispatcher = new MockTimeEventDispatcher();
	private CdiTestSignalDispatcher signalDispatcher = new CdiTestSignalDispatcher();
	private StatefulKnowledgeSession knowledgeSession;
	private Session hibernateSession;
	private SessionFactory sessionFactory;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	private MockServletLifecycle lifecycle = new MockServletLifecycle();
	private MockDeployment deployment;
	private MockBeanDeploymentArchive jar;
	private Stack<Object> componentStack = new Stack<Object>();
	public static CdiTestEnvironment getInstance(){
		if(!(instance.get() instanceof CdiTestEnvironment)){
			instance.set(new CdiTestEnvironment());
		}
		return (CdiTestEnvironment) instance.get();
	}
	@Override
	public <T>T getComponent(Class<T> clazz){
		return this.getComponent(clazz, DefaultLiteral.INSTANCE);
	}
	
	@Override
	public <T>T getComponent(Class<T> clazz, Annotation qualifiers){
		try{
			if(clazz == ITimeEventDispatcher.class){
				return clazz.cast(timeEventDispatcher);
			}else if(clazz == ISignalDispatcher.class){
				return clazz.cast(signalDispatcher);
			}else if(clazz == StatefulKnowledgeSession.class){
				if(resolveBean(Session.class, DefaultLiteral.INSTANCE) != null){
					// Hibernate Session present - prepare JBPM appropriately
					return clazz.cast(resolveBean(CdiTestJbpmKnowledgeSession.class, DefaultLiteral.INSTANCE).getKnowledgeSession());
				}else{
					// No Hibernate session, provide singleton knowledge session
					return clazz.cast(getKnowledgeSession());
				}
			}
			return resolveAndWrapBean(clazz, qualifiers);
		}catch(NoSuchMethodException e){
			throw new RuntimeException(e);
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e);
		}
	}
	private <T>T resolveAndWrapBean(Class<T> clazz,Annotation q) throws NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException{
		final T component = resolveBean(clazz, q);
		if(component != null){
			ProxyFactory proxyFactory = new ProxyFactory();
			if(clazz.isInterface()){
				proxyFactory.setInterfaces(new Class<?>[]{clazz});
			}else{
				proxyFactory.setSuperclass(clazz);
			}
			T proxy = clazz.cast(proxyFactory.create(new Class<?>[]{}, new Object[]{}, new MethodHandler(){
				@Override
				public Object invoke(Object self,Method thisMethod,Method proceed,Object[] args) throws Throwable{
					if(!thisMethod.getName().equals("finalize")){
						try{
							beforeRequest(component);
							return thisMethod.invoke(component, args);
						}finally{
							afterRequest(component);
						}
					}else{
						return thisMethod.invoke(component, args);
					}
				}
			}));
			return proxy;
		}else{
			return null;
		}
	}
	private <T>T resolveBean(Class<T> clazz,Annotation q){
		BeanManagerImpl beanManager = getBeanManager();
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(clazz, q));
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		final T component;
		if(bean != null){
			component = (T) beanManager.getReference(bean, clazz, ctx);
		}else{
			component = null;
		}
		return component;
	}
	public BeanManagerImpl getBeanManager(){
		return BeanManagerLocator.INSTANCE.locate();
	}
	public SessionFactory getHibernateSessionFactory(){
		if(this.sessionFactory == null){
			try{
				long start = System.currentTimeMillis();
				Configuration hibernateConfiguration = new Configuration();
				hibernateConfiguration.configure(getHibernateConfigName());
				hibernateConfiguration.getTypeResolver().registerTypeOverride(PostgresDialect.PostgresqlMateralizedBlobType.INSTANCE);
				ConnectionProvider connProvider = ConnectionProviderFactory.newConnectionProvider(hibernateConfiguration.getProperties());
				Connection connection = connProvider.getConnection();
				Statement st = connection.createStatement();
				st.executeUpdate("CREATE SCHEMA ERICSSON_GSM AUTHORIZATION DBA");
				st.executeUpdate("CREATE SCHEMA ERICSSON_UMTS AUTHORIZATION DBA");
				st.executeUpdate("CREATE SCHEMA HUAWEI_GSM AUTHORIZATION DBA");
				st.executeUpdate("CREATE SCHEMA HUAWEI_UMTS AUTHORIZATION DBA");
				st.executeUpdate("CREATE SCHEMA SIEMENS_GSM AUTHORIZATION DBA");
				st.executeUpdate("CREATE SCHEMA CM AUTHORIZATION DBA");
				connection.commit();
				st.close();
				connection.close();
				SchemaExport se = new SchemaExport(hibernateConfiguration);
				se.create(false, true);
				sessionFactory = hibernateConfiguration.buildSessionFactory();
				System.out.println("Building session factory took " + (System.currentTimeMillis() - start) + "ms");
			}catch(Throwable e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return sessionFactory;
	}
	public void reset(){
		signalDispatcher.reset();
		timeEventDispatcher.reset();
		knowledgeSession = null;
		// TODO this should not be necessary
		abstractJbpmKnowledgeBase = null;
		if(hibernateSession != null){
			hibernateSession.clear();
		}
	}
	private StatefulKnowledgeSession getKnowledgeSession(){
		if(this.knowledgeSession == null){
			// TODO attach jbpm to whichever Hibernate session being used.
			// TODO remember to mock the jbpm event query when using the mock hibernate session
			if(this.abstractJbpmKnowledgeBase == null){
				this.abstractJbpmKnowledgeBase = createJbpmKnowledgeBase();
			}
			Properties props = new Properties();
			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
			SessionConfiguration cfg = new SessionConfiguration(props);
			EnvironmentImpl env = new EnvironmentImpl();
			this.knowledgeSession = abstractJbpmKnowledgeBase.getKnowledgeBase().newStatefulKnowledgeSession(cfg, env);
		}
		return knowledgeSession;
	}
	protected AbstractJbpmKnowledgeBase createJbpmKnowledgeBase(){
		return (AbstractJbpmKnowledgeBase) instantiateImplementation(JBPM_KNOWLEDGE_BASE_IMPLEMENTATION);
	}
	protected String getHibernateConfigName(){
		return "standalone-" + loadProperties().getProperty(HIBERNATE_CONFIG_NAME);
	}
	public void initializeDeployment(Collection<String> beansXmlFiles,final List<Class<?>> allBeansList){
		jar = new MockBeanDeploymentArchive();
		deployment = new MockDeployment(jar);
		Collection<URL> result = new HashSet<URL>();
		for(String string:beansXmlFiles){
			result.add(Thread.currentThread().getContextClassLoader().getResource(string));
		}
		jar.setBeansXmlFiles(result);
		jar.setBeanClasses(allBeansList);
		lifecycle = new MockServletLifecycle(deployment, jar);
		lifecycle.initialize();
		lifecycle.beginApplication();
	}
	public void afterRequest(Object component){
		Object pop = componentStack.pop();
		if(pop != component){
			throw new IllegalStateException("Expcected " + pop.getClass() + " but was given " + component.getClass());
		}
		if(componentStack.isEmpty()){
			try{
				resolveBean(CdiTestSeamTransaction.class, DefaultTransactionLiteral.INSTANCE).commit();
				signalDispatcher.prepareSignalsForDispatch();
				resolveBean(Session.class,DefaultLiteral.INSTANCE).close();
			}catch(RuntimeException e){
				throw e;
			}catch(Exception e){
				throw new RuntimeException(e);
			}
			lifecycle.endRequest();
			lifecycle.endSession();
			signalDispatcher.deliverAllPendingSignals();
			
		}
	}
	public void beforeRequest(Object component){
		if(this.componentStack.isEmpty()){
			lifecycle.beginSession();
			lifecycle.beginRequest();
			try{
				resolveBean(CdiTestSeamTransaction.class, DefaultTransactionLiteral.INSTANCE).begin();
			}catch(RuntimeException e){
				throw e;
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		componentStack.push(component);
	}
	public static List<Class<?>> getMockClasses(){
		return Arrays.asList(CditTestLogger.class, CdiTestHibernateSession.class, CdiTestSeamTransaction.class);
	}
	@Override
	public <T>Class<T> getImplementationClass(T o){
		if(!o.getClass().isSynthetic() && !o.getClass().getName().contains("$$")){
			return (Class<T>) o.getClass();
		}else{
			Class<T> c = (Class<T>) IntrospectionUtil.getOriginalClass(o);
			if(c == Object.class){
				// injected by interface
				BeanManagerImpl beanManager = getBeanManager();
				Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(o.getClass().getInterfaces()[0], DefaultLiteral.INSTANCE));
				Set<Type> types = bean.getTypes();
				for(Type type:types){
					if(type instanceof Class && !((Class<?>) type).isInterface() && type != Object.class){
						return (Class<T>) type;
					}
				}
			}
			return c;
		}
	}
}
