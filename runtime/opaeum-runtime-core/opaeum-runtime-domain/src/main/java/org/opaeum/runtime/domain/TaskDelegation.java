package org.opaeum.runtime.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(applicationIdentifier="opaeum-runtime-bpm",uuid="OpaeumBPMCommon.library.uml@_5lxp4LRZEeCilvbXE8KmHA")
public enum TaskDelegation implements IEnum, Serializable {
	POTENTIALOWNERS("OpaeumBPMCommon.library.uml@_MmeToLRaEeCilvbXE8KmHA",3561801672124045355l),
	NOBODY("OpaeumBPMCommon.library.uml@_OFIC8LRaEeCilvbXE8KmHA",683908055359230403l),
	ANYBODY("OpaeumBPMCommon.library.uml@_PFIHYLRaEeCilvbXE8KmHA",7195301974305942749l),
	OTHER("OpaeumBPMCommon.library.uml@_PtH7gLRaEeCilvbXE8KmHA",760436101290652573l);
	private long opaeumId;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for TaskDelegation
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private TaskDelegation(String uuid, long opaeumId) {
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
	
	static public Set<TaskDelegation> getValues() {
		return new HashSet<TaskDelegation>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}