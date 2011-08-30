package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.request_object",uuid="8e1632c7_9456_4529_addf_3ffecb9e70ac")
public interface RequestObject extends CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	@NumlMetaInfo(qualifiedPersistentName="request_object.request_id",uuid="af8c4bf5_e317_4ce5_9b3f_3f1e462d658c")
	public AbstractRequest getRequest();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}