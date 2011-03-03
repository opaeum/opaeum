package net.sf.nakeduml.jbpm.obsolete;

import org.drools.common.InternalKnowledgeRuntime;
import org.jbpm.process.instance.event.SignalManager;
import org.jbpm.process.instance.event.SignalManagerFactory;

public class HibernateSignalManagerFactory implements SignalManagerFactory {

	@Override
	public SignalManager createSignalManager(InternalKnowledgeRuntime kruntime) {
		return new HibernateSignalManager(kruntime);
	}
}
