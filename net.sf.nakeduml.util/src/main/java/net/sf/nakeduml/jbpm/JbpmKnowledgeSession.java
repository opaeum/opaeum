package net.sf.nakeduml.jbpm;

import javax.enterprise.context.ContextNotActiveException;

import net.sf.nakeduml.seam.Component;

import org.hibernate.Session;

public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession {
	static private JbpmKnowledgeSession mockInstance;

	public Session getEntityManager() {
		return Component.INSTANCE.getInstance(Session.class);
	}

	static public JbpmKnowledgeSession getInstance() {
		try {
			return Component.INSTANCE.getInstance(JbpmKnowledgeSession.class);
		} catch (ContextNotActiveException e) {
			if (mockInstance == null) {
				mockInstance = new JbpmKnowledgeSession();
			}
			return mockInstance;
		}
	}

	public AbstractJbpmKnowledgeBase getJbpmKnowledgeBase() {
		return JbpmKnowledgeBase.getInstance();
	}
}
