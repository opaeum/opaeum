package org.opaeum.runtime.contact;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="opaeum-runtime-bpm",uuid="252060@_YLfSwEtnEeGd4cpyhpib9Q")
public enum PersonEMailAddressType implements IEnum, Serializable {
	WORK("252060@_b0CGwEtnEeGd4cpyhpib9Q",2569478031111177750l),
	HOME("252060@_clbHcEtnEeGd4cpyhpib9Q",6316649075823617580l);
	private long opaeumId;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for PersonEMailAddressType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private PersonEMailAddressType(String uuid, long opaeumId) {
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
	
	static public Set<PersonEMailAddressType> getValues() {
		return new HashSet<PersonEMailAddressType>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}