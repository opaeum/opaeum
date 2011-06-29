package org.nakeduml.audit;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEntityEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.Initializable;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.hibernate.tuple.StandardProperty;
import org.jboss.logging.Logger;
import org.nakeduml.hibernate.domain.EventDispatcher;
import org.nakeduml.runtime.domain.Auditable;
import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.RevisionType;

public class AuditListener implements PostInsertEventListener,PostUpdateEventListener,Initializable,FlushEntityEventListener{
	private static final long serialVersionUID = -233067098331332700L;
	private static final Logger log = Logger.getLogger(AuditListener.class);
	private AuditConfiguration verCfg;
	@Override
	public void onPostUpdate(PostUpdateEvent event){
		copyAndRegister(event);
	}
	@Override
	public void onPostInsert(PostInsertEvent event){
		copyAndRegister(event);
	}
	@Override
	public void initialize(Configuration cfg){
		verCfg = AuditConfiguration.getFor(cfg);
	}
	private void copyAndRegister(PostInsertEvent event){
		if(event.getEntity() instanceof Auditable){
			AuditSync verSync = verCfg.getSyncManager().get(event.getSession());
			Auditable auditable = (Auditable) event.getEntity();
			Audited auditCopy = auditable.makeAuditCopy();
			auditCopy.setRevisionType(RevisionType.ADD);
			verSync.addAudited(auditCopy);
		}
	}
	private void copyAndRegister(PostUpdateEvent event){
		if(event.getEntity() instanceof Auditable){
			AuditSync verSync = verCfg.getSyncManager().get(event.getSession());
			Auditable auditable = (Auditable) event.getEntity();
			Audited auditCopy = auditable.makeAuditCopy();
			Audited audited = (Audited) auditCopy;
			audited.setRevisionType(RevisionType.MOD);
			verSync.addAudited(auditCopy);
		}
	}
	@Override
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException{
		//New idea
		int[] dirtyProperties = event.getDirtyProperties();
		StandardProperty[] properties = event.getEntityEntry().getPersister().getEntityMetamodel().getProperties();
		for(int i:dirtyProperties){
			
			Object value = event.getPropertyValues()[i];
			String name = properties[i].getName();
		}
		
		
	}
}
