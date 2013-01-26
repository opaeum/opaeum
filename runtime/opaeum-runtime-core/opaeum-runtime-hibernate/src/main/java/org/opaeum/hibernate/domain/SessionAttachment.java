package org.opaeum.hibernate.domain;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.event.spi.EventSource;
import org.opaeum.audit.AuditWorkUnit;
import org.opaeum.runtime.domain.IEventGenerator;

public class SessionAttachment {
	private Set<IEventGenerator> eventGenerators;
	private AuditWorkUnit auditWorkUnit;
	private EventSource session;
	private AbstractHibernatePersistence persistence;
	private long startTime=System.currentTimeMillis();
	private String startingThread;
	private boolean hasBeenLogged=false;

	public SessionAttachment(EventSource session) {
		super();
		this.session = session;
		StringWriter sw= new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		new Exception().printStackTrace(pw);
		pw.flush();
		pw.close();
		startingThread=sw.toString();
	}

	public void addEventGenerator(IEventGenerator entity) {
		if (eventGenerators == null) {
			eventGenerators = Collections.newSetFromMap(new WeakHashMap<IEventGenerator,Boolean>());
		}
		eventGenerators.add(entity);
	}

	public Set<IEventGenerator> getEventGenerators() {
		return eventGenerators == null ? Collections
				.<IEventGenerator> emptySet() : eventGenerators;
	}

	public AuditWorkUnit getAuditWorkUnit() {
		if (auditWorkUnit == null) {
			auditWorkUnit = new AuditWorkUnit(session);
		}
		return auditWorkUnit;
	}

	public AbstractHibernatePersistence getPersistence() {
		if (persistence == null) {
			persistence = new AbstractHibernatePersistence(session) {
			};
		}
		return persistence;
	}

	public void clearAuditWorkUnit() {
		auditWorkUnit = null;
	}

	public void cleanUp() {
		auditWorkUnit = null;
		if (eventGenerators != null) {
			eventGenerators.clear();
		}
		if(persistence!=null){
			persistence.cleanUp();
		}
		eventGenerators = null;

		// TODO Auto-generated method stub

	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public String getStartingThread() {
		return startingThread;
	}

	public void setStartingThread(String startingThread) {
		this.startingThread = startingThread;
	}
	public void logged(){
		hasBeenLogged=true;
	}
	public boolean hasBeenLogged() {
		return hasBeenLogged;
	}
}
