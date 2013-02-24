package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_zGMYgEvREeGmqIr8YsFD4g")
public interface IBusinessCollaboration extends IBusinessCollaborationBase, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor);
	
	public void addToBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearBusinessCollaboration_BusinessActor_businessActor();
	
	public void clearBusinessCollaboration_Business_business();
	
	public BusinessNetworkFacilatatesCollaboration createBusinessNetworkFacilatatesCollaboration_businessNetwork();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<? extends IBusiness> getBusiness();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<? extends IBusinessActor> getBusinessActor();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6013815494391219680l,opposite="businessCollaboration",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public Set<BusinessCollaboration_BusinessActor> getBusinessCollaboration_BusinessActor_businessActor();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4027588851766867750l,opposite="businessCollaboration",uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public Set<BusinessCollaboration_Business> getBusinessCollaboration_Business_business();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8276244440019438797l,opposite="businessCollaboration",uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6711375140756202784l,opposite="businessCollaboration",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor);
	
	public void removeFromBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business);
	
	public void setBusinessCollaboration_BusinessActor_businessActor(Set<BusinessCollaboration_BusinessActor> businessCollaboration_BusinessActor_businessActor);
	
	public void setBusinessCollaboration_Business_business(Set<BusinessCollaboration_Business> businessCollaboration_Business_business);
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork);
	
	public void setBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor);
	
	public void z_internalAddToBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business);
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork);
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);
	
	public void z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor);
	
	public void z_internalRemoveFromBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business);
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork);
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);

}