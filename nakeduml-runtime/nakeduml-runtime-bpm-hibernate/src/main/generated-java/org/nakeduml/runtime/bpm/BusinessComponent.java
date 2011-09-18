package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessCalendar;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.business_component",uuid="252060@_uVek8IoVEeCLqpffVZYAlw")
public interface BusinessComponent extends Participant, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(qualifiedPersistentName="business_component.business_calendar_id",uuid="252060@_ymlNQNcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessCalendar(BusinessCalendar businessCalendar);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val);
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val);

}