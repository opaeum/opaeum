package org.opaeum.environment.adaptor;

import java.util.List;

import org.drools.persistence.info.SessionInfo;
import org.drools.persistence.info.WorkItemInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.persistence.ProcessPersistenceContext;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;

public class HibernateProcessPersistenceContext implements ProcessPersistenceContext{
	private Session session;
	public HibernateProcessPersistenceContext(Session session){
		this.session = session;
	}
	public Session getSession(){
		return this.session;
	}
	@Override
	public void close(){
		throw new IllegalStateException("not supported");
	}
	@Override
	public SessionInfo findSessionInfo(Integer arg0){
		return (SessionInfo) session.get(SessionInfo.class, arg0);
	}
	@Override
	public WorkItemInfo findWorkItemInfo(Long arg0){
		return (WorkItemInfo) session.get(WorkItemInfo.class, arg0);
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
		session.persist(arg0);
	}
	@Override
	public void persist(WorkItemInfo arg0){
		session.persist(arg0);
	}
	@Override
	public void remove(WorkItemInfo arg0){
		session.delete(arg0);
	}
	@Override
	public void persist(ProcessInstanceInfo processInstanceInfo){
		session.persist(processInstanceInfo);
	}
	@Override
	public ProcessInstanceInfo findProcessInstanceInfo(Long processId){
		return (ProcessInstanceInfo) session.get(ProcessInstanceInfo.class, processId);
	}
	@Override
	public void remove(ProcessInstanceInfo processInstanceInfo){
		session.delete(processInstanceInfo);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getProcessInstancesWaitingForEvent(String type){
		Query processInstancesForEvent = session.getNamedQuery("ProcessInstancesWaitingForEvent");
		processInstancesForEvent.setParameter("type", type);
		return (List<Long>) processInstancesForEvent.list();
	}
}
