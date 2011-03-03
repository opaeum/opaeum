package net.sf.nakeduml.jbpm.obsolete;

import org.drools.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.ProcessInstanceManager;
import org.jbpm.process.instance.ProcessInstanceManagerFactory;

public class HibernateProcessInstanceManagerFactory implements ProcessInstanceManagerFactory {

	@Override
	public ProcessInstanceManager createProcessInstanceManager(InternalKnowledgeRuntime kruntime) {
		HibernateProcessInstanceManager result = new HibernateProcessInstanceManager();
		result.setKnowledgeRuntime(kruntime);
		return result;	}

}
