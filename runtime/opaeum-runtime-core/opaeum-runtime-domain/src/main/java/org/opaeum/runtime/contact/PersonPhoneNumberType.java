package org.opaeum.runtime.contact;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="opaeum-runtime-bpm",uuid="252060@_Z-VwcEtnEeGd4cpyhpib9Q")
public enum PersonPhoneNumberType implements IEnum, Serializable {
	HOME("252060@_do3hcEtnEeGd4cpyhpib9Q",9013144590782986798l),
	CELL("252060@_et7FAEtnEeGd4cpyhpib9Q",1762128726551794158l),
	WORK("252060@_e0fAAEtnEeGd4cpyhpib9Q",7683582777343226626l),
	FAX("252060@_e4mrAEtnEeGd4cpyhpib9Q",4389408779772903514l);
	private long opaeumId;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for PersonPhoneNumberType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private PersonPhoneNumberType(String uuid, long opaeumId) {
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
	
	static public Set<PersonPhoneNumberType> getValues() {
		return new HashSet<PersonPhoneNumberType>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}