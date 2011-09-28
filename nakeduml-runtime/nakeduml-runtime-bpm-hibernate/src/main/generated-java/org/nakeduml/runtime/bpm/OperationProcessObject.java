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

@NumlMetaInfo(uuid="252060@_5DVD4I3oEeCfQedkc0TCdA")
public interface OperationProcessObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	public ProcessRequest getProcessRequest();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}