package org.nakeduml.runtime.domain;




public interface Auditable extends HibernateEntity,IPersistentObject {
	public Audited makeAuditCopy();
}
