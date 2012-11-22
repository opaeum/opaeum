package org.opaeum.hibernate.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.event.EventSource;
import org.opaeum.audit.AuditWorkUnit;
import org.opaeum.runtime.domain.IEventGenerator;

public class SessionAttachment {
	private Set<IEventGenerator> eventGenerators;
	private AuditWorkUnit auditWorkUnit;
	private EventSource session;
	private AbstractHibernatePersistence persistence;
	
	public SessionAttachment(EventSource session) {
		super();
		this.session = session;
	}
	public void addEventGenerator(IEventGenerator entity) {
		if(eventGenerators==null){
			eventGenerators=new HashSet<IEventGenerator>();
		}
		eventGenerators.add(entity);
	}
	public Set<IEventGenerator> getEventGenerators() {
		return eventGenerators==null?Collections.<IEventGenerator>emptySet():eventGenerators;
	}
	public AuditWorkUnit getAuditWorkUnit() {
		if(auditWorkUnit==null){
			auditWorkUnit=new AuditWorkUnit(session);
		}
		return auditWorkUnit;
	}
	public AbstractHibernatePersistence getPersistence() {
		if(persistence==null){
			persistence=new AbstractHibernatePersistence(session) {
			};
		}
		return persistence;
	}
}
