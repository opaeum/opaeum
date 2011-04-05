package org.nakeduml.audit;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.Initializable;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.nakeduml.runtime.domain.Auditable;
import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.RevisionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditListener extends AbstractFlushingEventListener implements PostInsertEventListener, PostUpdateEventListener, FlushEventListener, Initializable{

	private static final long serialVersionUID = -233067098331332700L;
	private static final Logger log = LoggerFactory.getLogger(AuditListener.class);
	private AuditConfiguration verCfg;

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		copyAndRegister(event);
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		copyAndRegister(event);
	}

	@Override
	public void initialize(Configuration cfg) {
		verCfg = AuditConfiguration.getFor(cfg);
	}
	
	private void copyAndRegister(PostInsertEvent event) {
		if (event.getEntity() instanceof Auditable) {
			AuditSync verSync = verCfg.getSyncManager().get(event.getSession());
			Auditable auditable = (Auditable)event.getEntity();
			Audited auditCopy = auditable.makeAuditCopy();
			auditCopy.setRevisionType(RevisionType.ADD);
			verSync.addAudited(auditCopy);
		}		
	}
	
	private void copyAndRegister(PostUpdateEvent event) {
		if (event.getEntity() instanceof Auditable) {
			AuditSync verSync = verCfg.getSyncManager().get(event.getSession());
			Auditable auditable = (Auditable)event.getEntity();
			Audited auditCopy = auditable.makeAuditCopy();
			Audited audited = (Audited)auditCopy;
			audited.setRevisionType(RevisionType.MOD);
			verSync.addAudited(auditCopy);
		}
	}

	@Override
	public void onFlush(FlushEvent event) throws HibernateException {
		final EventSource source = event.getSession();
		
		
		if ( source.getPersistenceContext().hasNonReadOnlyEntities() ) {
			
			flushEverythingToExecutions(event);
			performExecutions(source);
			postFlush(source);
		
			if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
				source.getFactory().getStatisticsImplementor().flush();
			}
			
		}		
	}
	
	protected void performExecutions(EventSource session) throws HibernateException {

		log.trace("executing flush");
		session.getPersistenceContext().setFlushing(true); 
		try {
			session.getJDBCContext().getConnectionManager().flushBeginning();
			// we need to lock the collection caches before
			// executing entity inserts/updates in order to
			// account for bidi associations
			session.getActionQueue().prepareActions();
			session.getActionQueue().executeActions();
		}
		catch (HibernateException he) {
//			log.error("Could not synchronize database state with session", he);
			throw he;
		}
		finally {
			session.getPersistenceContext().setFlushing(false); 
			session.getJDBCContext().getConnectionManager().flushEnding();
		}
	}	

}
