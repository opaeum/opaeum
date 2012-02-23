package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g")
public interface IBusinessCollaboration extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToBusinessActor(IBusinessActor businessActor);
	
	public void addToBusinessComponent(IBusiness businessComponent);
	
	public void addToIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 iBusinessCollaboration_iBusinessActor_1_businessActor);
	
	public void addToIBusinessCollaboration_iBusinessComponent_1_businessComponent(IBusinessCollaboration_iBusinessComponent_1 iBusinessCollaboration_iBusinessComponent_1_businessComponent);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearBusinessActor();
	
	public void clearBusinessComponent();
	
	public void clearIBusinessCollaboration_iBusinessActor_1_businessActor();
	
	public void clearIBusinessCollaboration_iBusinessComponent_1_businessComponent();
	
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<IBusinessActor> getBusinessActor();
	
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<IBusiness> getBusinessComponent();
	
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork();
	
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork();
	
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public Set<IBusinessCollaboration_iBusinessActor_1> getIBusinessCollaboration_iBusinessActor_1_businessActor();
	
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public Set<IBusinessCollaboration_iBusinessComponent_1> getIBusinessCollaboration_iBusinessComponent_1_businessComponent();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessActor(Set<IBusinessActor> businessActor);
	
	public void setBusinessComponent(Set<IBusiness> businessComponent);
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork);
	
	public void setBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);
	
	public void setIBusinessCollaboration_iBusinessActor_1_businessActor(Set<IBusinessCollaboration_iBusinessActor_1> iBusinessCollaboration_iBusinessActor_1_businessActor);
	
	public void setIBusinessCollaboration_iBusinessComponent_1_businessComponent(Set<IBusinessCollaboration_iBusinessComponent_1> iBusinessCollaboration_iBusinessComponent_1_businessComponent);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessActor(IBusinessActor val);
	
	public void z_internalAddToBusinessComponent(IBusiness val);
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork val);
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val);
	
	public void z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalAddToIBusinessCollaboration_iBusinessComponent_1_businessComponent(IBusinessCollaboration_iBusinessComponent_1 val);
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor val);
	
	public void z_internalRemoveFromBusinessComponent(IBusiness val);
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork val);
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(IBusinessCollaboration_iBusinessActor_1 val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessComponent(IBusinessCollaboration_iBusinessComponent_1 val);

}