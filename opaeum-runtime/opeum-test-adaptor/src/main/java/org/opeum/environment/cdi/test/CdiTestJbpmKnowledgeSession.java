package org.opaeum.environment.cdi.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.runtime.Environment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.opaeum.environment.adaptor.HibernateProcessPersistenceContextManager;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeSession;


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
	protected ObjectMarshallingStrategy getPlaceholderResolverStrategy(Environment environment){
		return null;
	}

	@Override
	protected ProcessPersistenceContextManager getPersistenceContextManager(Environment environment){
		return new HibernateProcessPersistenceContextManager(CdiTestEnvironment.getInstance().getComponent(Session.class));
	}

	

}
