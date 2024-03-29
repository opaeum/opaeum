package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessBase;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_G6MA0FYtEeGj5_I7bIwNoA")
public interface IBusiness extends IBusinessBase, HibernateEntity, CompositionNode, Serializable, IBusinessComponent, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2952021989536159761l,opposite="business",uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}