package org.opaeum.demo1.structuredbusiness.appliance;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_z8IcwHsKEeGBGZr9IpIa3A")
public enum Vendor implements IEnum, Serializable {
	WHIRLPOOL("914890@_19w9UHsKEeGBGZr9IpIa3A",7658578000613130252l),
	BOSCH("914890@_3FQw4HsKEeGBGZr9IpIa3A",377325257822563020l),
	KELVINATOR("914890@_4C-9YHsKEeGBGZr9IpIa3A",7281287795211476570l);
	private long opaeumId;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for Vendor
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Vendor(String uuid, long opaeumId) {
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
	
	static public Set<Vendor> getValues() {
		return new HashSet<Vendor>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}