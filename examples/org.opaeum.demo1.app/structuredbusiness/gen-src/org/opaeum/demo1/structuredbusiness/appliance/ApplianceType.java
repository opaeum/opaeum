package org.opaeum.demo1.structuredbusiness.appliance;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_8mTZ0JK_EeGnpuq6_ber_Q")
public enum ApplianceType implements IEnum, Serializable {
	DISHWASHER("914890@_9uUx0JK_EeGnpuq6_ber_Q",7669130221086869555l),
	TUMBLEDRYER("914890@_-uwUEJK_EeGnpuq6_ber_Q",1021599685950688687l),
	WASHINGMACHINE("914890@_BBJNcJLAEeGnpuq6_ber_Q",1858247726865489363l);
	private long opaeumId;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for ApplianceType
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private ApplianceType(String uuid, long opaeumId) {
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
	
	static public Set<ApplianceType> getValues() {
		return new HashSet<ApplianceType>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}