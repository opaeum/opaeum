package org.opaeum.audit;

import org.hibernate.event.EventSource;
import org.opaeum.hibernate.domain.SessionAttachment;
import org.opaeum.runtime.environment.Environment;

public class AuditSessionAttachment extends SessionAttachment{
	private AuditWorkUnit auditWorkUnit;
	public AuditSessionAttachment(EventSource session,Environment env){
		super(session, env);
	}
	public AuditWorkUnit getAuditWorkUnit(){
		if(auditWorkUnit == null){
			auditWorkUnit = new AuditWorkUnit(session);
		}
		return auditWorkUnit;
	}
	public void clearAuditWorkUnit(){
		auditWorkUnit = null;
	}
	@Override
	public void cleanUp(){
		auditWorkUnit = null;
		super.cleanUp();
	}
}
