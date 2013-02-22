package org.opaeum.runtime.bpm.contact;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_58k8wEtpEeGd4cpyhpib9Q")
public enum OrganizationEMailAddressType implements IEnum, Serializable {
	INFO("252060@_746GkEtpEeGd4cpyhpib9Q",3394756903880631170l),
	ADMIN("252060@_z4RAUFYyEeGj5_I7bIwNoA",2861333294210682425l);
	private long opaeumId;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for OrganizationEMailAddressType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private OrganizationEMailAddressType(String uuid, long opaeumId) {
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<OrganizationEMailAddressType> getValues() {
		return new HashSet<OrganizationEMailAddressType>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}