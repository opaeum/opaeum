package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.persistence.Embeddable;

@Embeddable
public class AuditId implements Serializable, Comparable<AuditId> {
	private static final long serialVersionUID = 6239242633981988358L;
	// @Column(name="`original_id`")
	private Long originalId;
	// @Column(name="`object_version`")
	private int objectVersion;

	public AuditId(String id) {
		StringTokenizer st = new StringTokenizer(id, "$");
		this.originalId = Long.parseLong(st.nextToken());
		this.objectVersion = Integer.parseInt(st.nextToken());
	}

	/**
	 * Constructor for AuditId
	 * 
	 * @param id
	 * @param objectVersion
	 */
	public AuditId(Long id, int objectVersion) {
		this.originalId = id;
		this.objectVersion = objectVersion;
	}

	/**
	 * Constructor for AuditId
	 */
	public AuditId() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuditId == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		AuditId rhs = (AuditId) obj;
		return toString().equals(rhs.toString());
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public int getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		return originalId + "$" + objectVersion;
	}

	@Override
	public int compareTo(AuditId o) {
		return toString().compareTo(o.toString());
	}

}
