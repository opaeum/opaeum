package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_2nANcEt8EeGElKTCe2jfDw")
public interface IResponsibilityInvocation extends HibernateEntity, CompositionNode, IRequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}