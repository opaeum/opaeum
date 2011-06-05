package org.nakeduml.hibernate.domain;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.nakeduml.runtime.domain.BaseAuditable;

public class PreInsertListener implements PreInsertEventListener{
	@Override
	public boolean onPreInsert(PreInsertEvent event){
		if(event.getEntity() instanceof BaseAuditable){
			BaseAuditable ba = (BaseAuditable) event.getEntity();
			ba.defaultCreate();
		}else if(event.getEntity() instanceof ProcessInstanceInfo){
			ProcessInstanceInfo pi = (ProcessInstanceInfo) event.getEntity();
			pi.update();
		}
		return false;
	}
}
