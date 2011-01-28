package net.sf.nakeduml.util;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.persistence.processinstance.JPAWorkItemManager;
import org.drools.process.instance.WorkItemManager;
import org.drools.process.instance.WorkItemManagerFactory;

public class HibernateWorkItemManagerFactory implements WorkItemManagerFactory {

	public WorkItemManager createWorkItemManager(InternalKnowledgeRuntime kruntime) {
		return new HibernateWorkItemManager(kruntime);
	}


}
