package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessComponentBase;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw")
public interface IBusinessComponent extends IBusinessComponentBase, Participant, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3245714109628633948l,opposite="businessComponent",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw@252060@_vf4noFYuEeGj5_I7bIwNoA")
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_representedOrganization();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8314504260854280851l,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	public OrganizationNode getRepresentedOrganization();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization);
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val);
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode val);
	
	public void z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val);
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode val);

}