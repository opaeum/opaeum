package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw")
public interface IBusinessActor extends Participant, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public OrganizationalNode getOrganization();
	
	@NumlMetaInfo(uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organization();
	
	@NumlMetaInfo(uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw252060@_X4-lcEtyEeGElKTCe2jfDw")
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPerson();
	
	@NumlMetaInfo(uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	public Person getRepresentedPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setOrganization(OrganizationalNode organization);
	
	public void setOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization);
	
	public void setPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson);
	
	public void setRepresentedPerson(Person representedPerson);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToOrganization(OrganizationalNode val);
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val);
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val);
	
	public void z_internalAddToRepresentedPerson(Person val);
	
	public void z_internalRemoveFromOrganization(OrganizationalNode val);
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val);
	
	public void z_internalRemoveFromPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val);
	
	public void z_internalRemoveFromRepresentedPerson(Person val);

}