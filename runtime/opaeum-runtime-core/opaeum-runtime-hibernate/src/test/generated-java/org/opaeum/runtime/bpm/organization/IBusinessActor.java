package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.INotificationReceiver;
import org.opaeum.runtime.organization.IBusinessActorBase;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_AN3QcEtxEeGElKTCe2jfDw")
public interface IBusinessActor extends HibernateEntity, CompositionNode, INotificationReceiver, Serializable, IBusinessActorBase, IPersistentObject, IParticipant {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=124417441767574741l,opposite="businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8203889236759279938l,opposite="businessActor",uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public OrganizationNode getOrganization();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1418802680892094436l,opposite="businessActor",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organization();
	
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organizationFor(OrganizationNode match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5948223451457234128l,opposite="businessActor",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_X4-lcEtyEeGElKTCe2jfDw")
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPerson();
	
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPersonFor(PersonNode match);
	
	@NumlMetaInfo(uuid="252060@_6Co6gO0lEeGN-aZ7URyUbw")
	public PersonNode getPersonNode();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9151265436220545056l,opposite="businessActor",uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	public PersonNode getRepresentedPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setOrganization(OrganizationNode organization);
	
	public void setRepresentedPerson(PersonNode representedPerson);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization);
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson);
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization);
	
	public void z_internalRemoveFromPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson);

}