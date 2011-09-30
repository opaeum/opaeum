package org.opeum.runtime.environment;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.opeum.runtime.domain.IActiveObject;
import org.opeum.runtime.domain.ISignal;
import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase;

public class MockEnvironment extends Environment{
	private Map<String, Object> components = new HashMap<String, Object>();
	private StatefulKnowledgeSession knowledgeSession;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	public static MockEnvironment getInstance(){
		defaultImplementation=MockEnvironment.class;
		return (MockEnvironment) Environment.getInstance();
	}
	public <T> void mockComponent(Class<T> clazz, T component) {
		this.components.put(clazz.getName(), component);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T>T getComponent(Class<T> clazz){
		if(clazz ==StatefulKnowledgeSession.class){
			return (T) getKnowledgeSession();	
		}
		return (T) components.get(clazz);
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return getComponent(clazz);
	}
	@Override
	public void reset(){
	}
	@Override
	public void endRequestContext(){
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public void sendSignal(IActiveObject target,ISignal s){
	}
	private StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			if (this.abstractJbpmKnowledgeBase == null) {
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

}
