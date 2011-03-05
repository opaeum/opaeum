package org.nakeduml.runtime.domain;





public interface Audited {
	AbstractEntity getOriginal();
	void setOriginal(AbstractEntity abstractEntity);
	void setRevision(RevisionEntity revisionEntity);
	RevisionEntity getRevision();
	RevisionType getRevisionType();
	void setRevisionType(RevisionType revisionType);
	void setPreviousVersion(Audited previousVersion);
	Audited getPreviousVersion();
	AuditId getId();
}
