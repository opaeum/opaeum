package org.opaeum.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw")
public interface BusinessComponent extends Participant, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_ymlNQNcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessCalendar(BusinessCalendar _businessCalendar);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val);
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val);

}