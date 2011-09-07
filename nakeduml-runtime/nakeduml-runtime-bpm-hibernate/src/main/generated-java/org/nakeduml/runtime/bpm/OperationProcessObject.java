package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.operation_process_object",uuid="OpiumBPM.library.uml@_5DVD4I3oEeCfQedkc0TCdA")
public interface OperationProcessObject extends HibernateEntity, CompositionNode, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	@NumlMetaInfo(qualifiedPersistentName="operation_process_object.process_request_id",uuid="OpiumBPM.library.uml@_JY15wY3pEeCfQedkc0TCdA")
	public ProcessRequest getProcessRequest();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}