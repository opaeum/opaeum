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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_G6MA0FYtEeGj5_I7bIwNoA")
public interface IBusiness extends IBusinessBase, HibernateEntity, CompositionNode, Serializable, IBusinessComponent, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public BusinessCollaboration_Business createBusinessCollaboration_Business_businessCollaboration();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2952021989536159761l,opposite="business",uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1212949578310605597l,opposite="business",uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_G6MA0FYtEeGj5_I7bIwNoA@252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public BusinessCollaboration_Business getBusinessCollaboration_Business_businessCollaboration();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessCollaboration_Business_businessCollaboration(BusinessCollaboration_Business businessCollaboration_Business_businessCollaboration);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCollaboration_Business_businessCollaboration(BusinessCollaboration_Business businessCollaboration_Business_businessCollaboration);
	
	public void z_internalRemoveFromBusinessCollaboration_Business_businessCollaboration(BusinessCollaboration_Business businessCollaboration_Business_businessCollaboration);

}