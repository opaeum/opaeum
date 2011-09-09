package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.business_component",uuid="OpiumBPM.library.uml@_uVek8IoVEeCLqpffVZYAlw")
public interface BusinessComponent extends Participant, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	@NumlMetaInfo(qualifiedPersistentName="business_component.business_calendar_id",uuid="OpiumBPM.library.uml@_ymlNQNcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void setBusinessCalendar(BusinessCalendar businessCalendar);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val);
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val);

}