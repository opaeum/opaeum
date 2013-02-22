package org.opaeum.runtime.bpm.businesscalendar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_EnGlsNb-EeCJ0dmaHEVVnw")
public enum WorkDayKind implements IEnum, Serializable {
	WEEKDAY("252060@_GJQU8Nb-EeCJ0dmaHEVVnw",1582201863518161267l),
	SATURDAY("252060@_H3nUMNb-EeCJ0dmaHEVVnw",6901241406743663175l),
	SUNDAY("252060@_IionINb-EeCJ0dmaHEVVnw",4486357431481835835l);
	private long opaeumId;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private String uuid;
	/** Constructor for WorkDayKind
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private WorkDayKind(String uuid, long opaeumId) {
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
	
	static public Set<WorkDayKind> getValues() {
		return new HashSet<WorkDayKind>(java.util.Arrays.asList(values()));
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}

}