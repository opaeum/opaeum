package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.business_role",uuid="0e0add46_a5da_4e51_a3db_9b16a244d0ab")
public interface BusinessRole extends Participant, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}