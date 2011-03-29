package org.nakeduml.environment.cdi.unittest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.Map.Entry;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.seam.solder.literal.DefaultLiteral;
import org.jboss.weld.Container;
import org.jboss.weld.bootstrap.spi.BeanDeploymentArchive;
import org.jboss.weld.manager.BeanManagerImpl;
import org.jboss.weld.mock.MockBeanDeploymentArchive;
import org.jboss.weld.mock.MockDeployment;
import org.jboss.weld.mock.MockServletLifecycle;
import org.jboss.weld.test.BeanManagerLocator;
import org.nakeduml.environment.AbstractJbpmKnowledgeBase;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.ITimeEventDispatcher;

public class CdiUnitTestEnvironment extends Environment{
	private MockTimeEventDispatcher timeEventDispatcher = new MockTimeEventDispatcher();
	private MockSignalDispatcher signalDispatcher = new MockSignalDispatcher();
	private StatefulKnowledgeSession knowledgeSession;
	private Session hibernateSession;
	private SessionFactory sessionFactory;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	private MockServletLifecycle lifecycle = new MockServletLifecycle();
	private MockDeployment deployment;
	private MockBeanDeploymentArchive jar;
	private Stack<Object> componentStack = new Stack<Object>();
	public static CdiUnitTestEnvironment getInstance(){
		if(!(instance.get() instanceof CdiUnitTestEnvironment)){
			instance.set(new CdiUnitTestEnvironment());
		}
		return (CdiUnitTestEnvironment) instance.get();
	}
	@Override
	public <T>T getComponent(Class<T> clazz){
		try{
			if(clazz == ITimeEventDispatcher.class){
				return (T) timeEventDispatcher;
			}else if(clazz == ISignalDispatcher.class){
				return (T) signalDispatcher;
			}else if(clazz == StatefulKnowledgeSession.class){
				return (T) getKnowledgeSession();
			}else if(clazz == Session.class){
				return (T) getHibernateSession();
			}
			Object object = new Object();
			beforeRequest(object);
			BeanManagerImpl beanManager = getBeanManager();
			Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(clazz, DefaultLiteral.INSTANCE));
			CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
			final T component = (T) beanManager.getReference(bean, clazz, ctx);
			ProxyFactory proxyFactory = new ProxyFactory();
			if(clazz.isInterface()){
				proxyFactory.setInterfaces(new Class<?>[]{clazz});
			}else{
				proxyFactory.setSuperclass(clazz);
			}
			T proxy = (T) proxyFactory.create(new Class<?>[]{}, new Object[]{}, new MethodHandler(){
				@Override
				public Object invoke(Object self,Method thisMethod,Method proceed,Object[] args) throws Throwable{
					try{
						beforeRequest(component);
						return thisMethod.invoke(component, args);
					}finally{
						afterRequest(component);
					}
				}
			});
			afterRequest(object);
			return proxy;
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
	public BeanManagerImpl getBeanManager(){
		return BeanManagerLocator.INSTANCE.locate();
	}
	private Session getHibernateSession(){
		if(this.hibernateSession == null){
			if(this.sessionFactory == null){
				Configuration hibernateConfiguration = new Configuration();
				hibernateConfiguration.configure(getHibernateConfigName());
				sessionFactory = hibernateConfiguration.buildSessionFactory();
			}
			this.hibernateSession = sessionFactory.openSession();
		}
		return this.hibernateSession;
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
		return loadProperties().getProperty(HIBERNATE_CONFIG_NAME);
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
		lifecycle.initialize();
		lifecycle.beginApplication();
	}
	public void afterRequest(Object component){
		Object pop = componentStack.pop();
		if(pop != component){
			throw new IllegalStateException("Expcected " + pop.getClass() + " but was given " + component.getClass());
		}
		if(componentStack.isEmpty()){
			lifecycle.endRequest();
			lifecycle.endSession();
			getComponent(ISignalDispatcher.class).deliverAllPendingSignals();
		}
	}
	public void beforeRequest(Object component){
		if(this.componentStack.isEmpty()){
			lifecycle.beginRequest();
			lifecycle.beginSession();
		}
		componentStack.push(component);
	}
}
