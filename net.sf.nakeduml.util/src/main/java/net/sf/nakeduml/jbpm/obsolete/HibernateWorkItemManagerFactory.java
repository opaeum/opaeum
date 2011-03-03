package net.sf.nakeduml.jbpm.obsolete;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.process.instance.WorkItemManager;
import org.drools.process.instance.WorkItemManagerFactory;

public class HibernateWorkItemManagerFactory implements WorkItemManagerFactory {

	public WorkItemManager createWorkItemManager(InternalKnowledgeRuntime kruntime) {
		return new HibernateWorkItemManager(kruntime);
	}


}
