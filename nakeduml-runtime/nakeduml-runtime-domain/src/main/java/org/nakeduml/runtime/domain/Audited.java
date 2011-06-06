package org.nakeduml.runtime.domain;





public interface Audited {
	Auditable getOriginal();
	void setOriginal(IPersistentObject abstractEntity);
	void setRevision(RevisionEntity revisionEntity);
	RevisionEntity getRevision();
	RevisionType getRevisionType();
	void setRevisionType(RevisionType revisionType);
	void setPreviousVersion(Audited previousVersion);
	Audited getPreviousVersion();
	AuditId getId();
	void setId(AuditId id);
}
