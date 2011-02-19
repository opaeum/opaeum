package net.sf.nakeduml.audit;

import java.io.Serializable;
import java.util.LinkedList;

import net.sf.nakeduml.util.Audited;
import net.sf.nakeduml.util.RevisionEntity;

public class AbstractWorkUnit implements Serializable {

	private static final long serialVersionUID = 1706148228216480337L;
	private RevisionEntity revisionEntity;
	private LinkedList<Audited> auditedEntities;
	
	public AbstractWorkUnit() {
		super();
	}

	public RevisionEntity getRevisionEntity() {
		return revisionEntity;
	}

	public void setRevisionEntity(RevisionEntity revisionEntity) {
		this.revisionEntity = revisionEntity;
	}

	public LinkedList<Audited> getAuditedEntities() {
		return auditedEntities;
	}

	public void setAuditedEntities(LinkedList<Audited> auditedEntities) {
		this.auditedEntities = auditedEntities;
	}

}
