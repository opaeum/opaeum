package org.opaeum.audit;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

@Embeddable
public final class AuditEntryId implements Serializable {
	@Basic
	@Column(name = "id")
	String id;
	@Transient
	String type;
	@Transient
	Long originalId;
	@Transient
	int objectVersion;
	private static final long serialVersionUID = 3809174462646076876L;

	public AuditEntryId(IPersistentObject e, int version) {
		super();
		init((Class<?>) IntrospectionUtil.getOriginalClass(e), e.getId(), version);
	}

	private void init(Class<?> originalClass, Long id2, int objectVersion) {
		String name = originalClass.getName();
		this.id = name + "~" + id2 + "~" + objectVersion;
		this.type = name;
		this.objectVersion = objectVersion;
		this.originalId = id2;
	}

	public AuditEntryId() {
		super();
	}

	public AuditEntryId(AuditEntry e) {
		init((Class<?>) e.getOriginalClass(), e.getOriginalId(), e.getObjectVersion());
	}

	public AuditEntryId(String stringValue) {
		this.id=stringValue;
	}

	public int hashCode() {
		return id.hashCode();
	}

	public boolean equals(Object other) {
		if (other instanceof AuditEntryId) {
			if (other == this) {
				return true;
			} else {
				AuditEntryId o = (AuditEntryId) other;
				return id.equals(o.id);
			}
		} else {
			return false;
		}
	}

	public AuditEntryId previousVersion() {
		parse();
		AuditEntryId result = new AuditEntryId();
		result.originalId = originalId;
		result.type = type;
		result.objectVersion = objectVersion - 1;
		result.id = type + "~" + originalId + "~" + result.objectVersion;
		return result;
	}

	private void parse() {
		if(originalId==null){
			String[] s= id.split("~");
			type=s[0];
			originalId=new Long(s[1]);
			objectVersion=Integer.parseInt(s[2]);
		}
	}

	public String getId() {
		return id;
	}
	public String toString(){
		return id;
	}
	public void updateVersion(int version) {
		parse();
		objectVersion=version;
		id = type + "~" + originalId + "~" + version;
		
	}

	public int getObjectVersion() {
		parse();
		return objectVersion;
	}

}