package org.nakeduml.audit;

import java.io.Serializable;
import java.util.LinkedList;

import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.RevisionEntity;

public class AbstractWorkUnit implements Serializable {
	int retryCount;
	private static final long serialVersionUID = 1706148228216480337L;
	private RevisionEntity revisionEntity;
	private LinkedList<Audited> auditedEntities;
	private long sequence;
	
	public AbstractWorkUnit() {
		super();
	}
	public void incrementRetryCount(){
		retryCount++;
	}
	public int getRetryCount(){
		return retryCount;
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
	
	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}	

}
