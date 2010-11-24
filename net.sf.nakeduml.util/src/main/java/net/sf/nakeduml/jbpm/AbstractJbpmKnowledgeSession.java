package net.sf.nakeduml.jbpm;

import java.util.Properties;

import javax.persistence.EntityManager;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.ProcessBuilderFactory;
import org.drools.impl.EnvironmentFactory;
import org.drools.impl.EnvironmentImpl;
import org.drools.io.ResourceFactory;
import org.drools.marshalling.impl.ProcessMarshallerFactory;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessRuntimeFactory;
import org.jboss.seam.contexts.Contexts;
import org.jbpm.marshalling.impl.ProcessMarshallerFactoryServiceImpl;
import org.jbpm.marshalling.impl.ProcessMarshallerRegistry;
import org.jbpm.process.builder.ProcessBuilderFactoryServiceImpl;
import org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.workflow.core.node.EndNode;
import org.jbpm.workflow.core.node.Join;
import org.jbpm.workflow.core.node.StateNode;
import org.jbpm.workflow.instance.impl.NodeInstanceFactoryRegistry;
import org.jbpm.workflow.instance.impl.factory.CreateNewNodeFactory;
import org.jbpm.workflow.instance.impl.factory.ReuseNodeFactory;

public abstract class AbstractJbpmKnowledgeSession {
	private StatefulKnowledgeSession knowledgeSession;

	protected abstract EntityManager getEntityManager();
	protected abstract AbstractJbpmKnowledgeBase getJbpmKnowledgeBase();
	public StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}
	
	protected StatefulKnowledgeSession createKnowledgeSession() {
		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
		if (Contexts.isEventContextActive()) {
			Properties properties = new Properties();
			properties.setProperty("drools.commandService", "org.drools.persistence.session.SingleSessionCommandService");
			properties.setProperty("drools.processInstanceManagerFactory",
					"org.drools.persistence.processinstance.JPAProcessInstanceManagerFactory");
			properties.setProperty("drools.workItemManagerFactory", "org.drools.persistence.processinstance.JPAWorkItemManagerFactory");
			properties.put("drools.processInstanceManagerFactory", "org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
			properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
			SessionConfiguration config = new SessionConfiguration(properties);
			Environment environment = EnvironmentFactory.newEnvironment();
			environment.set(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER, getEntityManager());
			return kbase.newStatefulKnowledgeSession(config, environment);
		} else {
			Properties props = new Properties();
			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
			SessionConfiguration cfg = new SessionConfiguration(props);
			EnvironmentImpl env = new EnvironmentImpl();
			return kbase.newStatefulKnowledgeSession(cfg, env);
		}
	}


}
