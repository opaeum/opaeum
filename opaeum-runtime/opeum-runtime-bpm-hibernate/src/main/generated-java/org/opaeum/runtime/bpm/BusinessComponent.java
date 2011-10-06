package org.opeum.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw")
public interface BusinessComponent extends Participant, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_ymlNQNcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessCalendar(BusinessCalendar businessCalendar);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val);
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val);

}