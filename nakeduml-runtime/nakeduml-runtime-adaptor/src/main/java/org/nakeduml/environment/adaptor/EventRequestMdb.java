package org.nakeduml.environment.adaptor;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.nakeduml.event.AbstractNakedUmlEvent;

public class EventRequestMdb extends AbstractSignalMdb<AbstractNakedUmlEvent>{
	@Inject
	Session hibernateSession;
	@Override
	protected void deliverMessage(AbstractNakedUmlEvent std) throws Exception{
		hibernateSession.clear();
		transaction.begin();
		if(std.isToBeCancelled()){
			Criteria c=hibernateSession.createCriteria(std.getClass());
			c.add(Restrictions.eq("eventSourceId", std.getEventSourceId()));
			c.add(Restrictions.eq("eventSourceClassName", std.getEventSourceClassName()));
			c.add(Restrictions.eq("callbackMethodName", std.getCallbackMethodName()));
			List<AbstractNakedUmlEvent> list = c.list();
			for(AbstractNakedUmlEvent event:list){
				hibernateSession.delete(event);
			}
		}else{
			hibernateSession.persist(std);
		}
		hibernateSession.flush();
		transaction.commit();
	}
	@Override
	protected String getQueueName(){
		return "queue/EventRequestQueue";
	}
}
