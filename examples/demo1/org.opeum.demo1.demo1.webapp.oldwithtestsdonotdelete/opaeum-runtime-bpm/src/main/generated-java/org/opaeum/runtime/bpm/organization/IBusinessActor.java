package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessActorBase;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw")
public interface IBusinessActor extends Participant, HibernateEntity, CompositionNode, Serializable, IBusinessActorBase, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=124417441767574741l,opposite="businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6660669114099610761l,opposite="businessActor",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration_iBusinessActor_1 getIBusinessCollaboration_iBusinessActor_1_businessCollaboration();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8203889236759279938l,opposite="businessActor",uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public OrganizationNode getOrganization();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1418802680892094436l,opposite="businessActor",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organization();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5948223451457234128l,opposite="businessActor",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_X4-lcEtyEeGElKTCe2jfDw")
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPerson();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9151265436220545056l,opposite="businessActor",uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	public PersonNode getRepresentedPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
	
	public void setOrganization(OrganizationNode organization);
	
	public void setOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization);
	
	public void setPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson);
	
	public void setRepresentedPerson(PersonNode representedPerson);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalAddToOrganization(OrganizationNode val);
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val);
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val);
	
	public void z_internalAddToRepresentedPerson(PersonNode val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalRemoveFromOrganization(OrganizationNode val);
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val);
	
	public void z_internalRemoveFromPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val);
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode val);

}