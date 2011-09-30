package org.opeum.runtime.jpa;

import java.util.Map;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.definition.process.Process;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.persistence.ProcessPersistenceContext;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.jbpm.persistence.processinstance.JPAProcessInstanceManager;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.jbpm.process.instance.ProcessInstanceManager;
import org.jbpm.process.instance.ProcessInstanceManagerFactory;
import org.jbpm.process.instance.impl.ProcessInstanceImpl;

public class SpecialJpaProcessInstanceManagerFactory implements ProcessInstanceManagerFactory {
	public ProcessInstanceManager createProcessInstanceManager(final InternalKnowledgeRuntime kruntime) {
		JPAProcessInstanceManager result = new JPAProcessInstanceManager() {
			private transient Map<Long, ProcessInstance> processInstances;

			@Override
			public ProcessInstance getProcessInstance(long id) {
				org.jbpm.process.instance.ProcessInstance processInstance = null;
				if (this.processInstances != null) {
					processInstance = (org.jbpm.process.instance.ProcessInstance) this.processInstances.get(id);
					if (processInstance != null) {
						return processInstance;
					}
				}

				ProcessPersistenceContext context = ((ProcessPersistenceContextManager) kruntime.getEnvironment().get(
						EnvironmentName.PERSISTENCE_CONTEXT_MANAGER)).getProcessPersistenceContext();
				ProcessInstanceInfo processInstanceInfo = context.findProcessInstanceInfo(id);
				if (processInstanceInfo == null) {
					return null;
				}
				//NB!! only difference is it does not update the last read date. We don't need this as we flush it in the EventDispatcher
				processInstance = (org.jbpm.process.instance.ProcessInstance) processInstanceInfo.getProcessInstance(kruntime, kruntime.getEnvironment());
				Process process = kruntime.getKnowledgeBase().getProcess(processInstance.getProcessId());
				if (process == null) {
					throw new IllegalArgumentException("Could not find process " + processInstance.getProcessId());
				}
				processInstance.setProcess(process);
				if (processInstance.getKnowledgeRuntime() == null) {
					processInstance.setKnowledgeRuntime(kruntime);
					((ProcessInstanceImpl) processInstance).reconnect();
				}
				return processInstance;
			}

		};
		result.setKnowledgeRuntime(kruntime);
		return result;
	}

}
