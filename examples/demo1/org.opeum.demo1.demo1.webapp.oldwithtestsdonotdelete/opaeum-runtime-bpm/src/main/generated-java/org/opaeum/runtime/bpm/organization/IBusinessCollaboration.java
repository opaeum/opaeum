package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g")
public interface IBusinessCollaboration extends IBusinessCollaborationBase, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 iBusinessCollaboration_iBusinessActor_1_businessActor);
	
	public void addToIBusinessCollaboration_iBusinessComponent_1_business(IBusinessCollaboration_iBusinessComponent_1 iBusinessCollaboration_iBusinessComponent_1_business);
	
	public void addToOrganization(OrganizationNode organization);
	
	public void addToPerson(PersonNode person);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearIBusinessCollaboration_iBusinessActor_1_businessActor();
	
	public void clearIBusinessCollaboration_iBusinessComponent_1_business();
	
	public void clearOrganization();
	
	public void clearPerson();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<IBusiness> getBusiness();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<IBusinessActor> getBusinessActor();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8276244440019438797l,opposite="businessCollaboration",uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6711375140756202784l,opposite="businessCollaboration",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6013815494391219680l,opposite="businessCollaboration",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public Set<IBusinessCollaboration_iBusinessActor_1> getIBusinessCollaboration_iBusinessActor_1_businessActor();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4027588851766867750l,opposite="businessCollaboration",uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public Set<IBusinessCollaboration_iBusinessComponent_1> getIBusinessCollaboration_iBusinessComponent_1_business();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5972556763473316153l,opposite="businessNetwork",uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	public Set<OrganizationNode> getOrganization();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2470938974911877691l,opposite="businessNetwork",uuid="252060@_3lOvoEvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_3lOvoEvREeGmqIr8YsFD4g")
	public Set<PersonNode> getPerson();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork);
	
	public void setBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);
	
	public void setIBusinessCollaboration_iBusinessActor_1_businessActor(Set<IBusinessCollaboration_iBusinessActor_1> iBusinessCollaboration_iBusinessActor_1_businessActor);
	
	public void setIBusinessCollaboration_iBusinessComponent_1_business(Set<IBusinessCollaboration_iBusinessComponent_1> iBusinessCollaboration_iBusinessComponent_1_business);
	
	public void setOrganization(Set<OrganizationNode> organization);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork val);
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val);
	
	public void z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalAddToIBusinessCollaboration_iBusinessComponent_1_business(IBusinessCollaboration_iBusinessComponent_1 val);
	
	public void z_internalAddToOrganization(OrganizationNode val);
	
	public void z_internalAddToPerson(PersonNode val);
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork val);
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_business(IBusinessCollaboration_iBusinessComponent_1 val);
	
	public void z_internalRemoveFromOrganization(OrganizationNode val);
	
	public void z_internalRemoveFromPerson(String username, PersonNode val);

}