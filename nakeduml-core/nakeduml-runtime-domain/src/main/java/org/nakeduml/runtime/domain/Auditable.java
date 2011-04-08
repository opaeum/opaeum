package org.nakeduml.runtime.domain;




public interface Auditable extends HibernateEntity,AbstractEntity {
	public Audited makeAuditCopy();
}
