package org.nakeduml.jbpm.domain;

import org.drools.runtime.StatefulKnowledgeSession;

public interface IJbpmKnowledgeSession {
	public abstract StatefulKnowledgeSession getKnowledgeSession();
}