package org.opeum.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IPersistentObject;
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