package net.sf.nakeduml.jbpm;

import org.drools.runtime.StatefulKnowledgeSession;

public interface IJbpmKnowledgeSession {
	public abstract StatefulKnowledgeSession getKnowledgeSession();
}