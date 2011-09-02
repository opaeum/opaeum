package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.business_component",uuid="b40869e0_bd1f_44f2_ae37_b9c2ab8c9b26")
public interface BusinessComponent extends Participant, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}