package org.nakeduml.environment.cdi.test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.nakeduml.environment.adaptor.AbstractJbpmKnowledgeSession;


public class CdiTestJbpmKnowledgeSession extends AbstractJbpmKnowledgeSession {
	static{
		CdiTestHibernateSession.addMockedQuery(new MockQuery(){
			@Override
			public boolean useFor(String query){
				return query.equals("ProcessInstancesWaitingForEvent");
			}
			@Override
			public List<Object> list() throws HibernateException{
				Collection<ProcessInstanceInfo> extent = session.getExtent(ProcessInstanceInfo.class);
				List<Object> result=new ArrayList<Object>();
				for(ProcessInstanceInfo p:extent){
					if(p.getEventTypes().contains(parameterMap.get("type"))){
						result.add(p);
					}
				}
				return result;
			}
		});
	}

	@Override
	public Session getHibernateSession(){
		return CdiTestEnvironment.getInstance().getComponent(Session.class);
	}

}
