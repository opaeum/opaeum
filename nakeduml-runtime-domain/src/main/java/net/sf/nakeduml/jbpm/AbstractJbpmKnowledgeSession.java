package net.sf.nakeduml.jbpm;

import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;

public abstract class AbstractJbpmKnowledgeSession implements IJbpmKnowledgeSession {
	private StatefulKnowledgeSession knowledgeSession;


	protected abstract AbstractJbpmKnowledgeBase getJbpmKnowledgeBase();

	/* (non-Javadoc)
	 * @see net.sf.nakeduml.jbpm.IJbpmKnowledgeSession#getKnowledgeSession()
	 */
	@Override
	public StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}

	protected StatefulKnowledgeSession createKnowledgeSession() {
		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
		Properties props = new Properties();
		props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
		props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
		SessionConfiguration cfg = new SessionConfiguration(props);
		EnvironmentImpl env = new EnvironmentImpl();
		return kbase.newStatefulKnowledgeSession(cfg, env);
	}
}
