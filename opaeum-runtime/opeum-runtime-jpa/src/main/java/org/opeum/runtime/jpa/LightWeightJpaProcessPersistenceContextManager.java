package org.opaeum.runtime.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.drools.persistence.PersistenceContext;
import org.drools.persistence.info.SessionInfo;
import org.drools.persistence.info.WorkItemInfo;
import org.jbpm.persistence.ProcessPersistenceContext;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;

public class LightWeightJpaProcessPersistenceContextManager implements ProcessPersistenceContextManager {

	public LightWeightJpaProcessPersistenceContextManager(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public static final class PPC implements ProcessPersistenceContext {
		private EntityManager entityManager;
		public PPC(EntityManager session){
			this.entityManager= session;
		}
		@Override
		public void close(){
			throw new IllegalStateException("not supported");
		}
		@Override
		public SessionInfo findSessionInfo(Integer arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public WorkItemInfo findWorkItemInfo(Long arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public boolean isOpen(){
			return false;
		}
		@Override
		public void joinTransaction(){
			throw new IllegalStateException("not supported");
		}
		@Override
		public WorkItemInfo merge(WorkItemInfo arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public void persist(SessionInfo arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public void persist(WorkItemInfo arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public void remove(WorkItemInfo arg0){
			throw new IllegalStateException("not supported");
		}
		@Override
		public void persist(ProcessInstanceInfo processInstanceInfo){
			entityManager.persist(processInstanceInfo);
		}
		@Override
		public ProcessInstanceInfo findProcessInstanceInfo(Long processId){
			return (ProcessInstanceInfo) entityManager.find(ProcessInstanceInfo.class, processId);
		}
		@Override
		public void remove(ProcessInstanceInfo processInstanceInfo){
			entityManager.remove(processInstanceInfo);
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<Long> getProcessInstancesWaitingForEvent(String type){
			javax.persistence.Query processInstancesForEvent = entityManager.createNamedQuery("ProcessInstancesWaitingForEvent");
			processInstancesForEvent.setParameter("type", type);
			return (List<Long>) processInstancesForEvent.getResultList();
		}
	}

	private EntityManager entityManager;

	@Override
	public PersistenceContext getApplicationScopedPersistenceContext() {
		throw new IllegalStateException();
	}

	@Override
	public PersistenceContext getCommandScopedPersistenceContext() {
		return new PPC(entityManager);
	}

	@Override
	public void beginCommandScopedEntityManager() {
		throw new IllegalStateException();
	}

	@Override
	public void endCommandScopedEntityManager() {
		throw new IllegalStateException();
	}

	@Override
	public void dispose() {
		throw new IllegalStateException();
	}

	@Override
	public ProcessPersistenceContext getProcessPersistenceContext() {
		return (ProcessPersistenceContext) getCommandScopedPersistenceContext();
	}

}
