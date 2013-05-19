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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_zGMYgEvREeGmqIr8YsFD4g")
public interface IBusinessCollaboration extends IBusinessCollaborationBase, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<? extends IBusiness> getBusiness();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<? extends IBusinessActor> getBusinessActor();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8276244440019438797l,opposite="businessCollaboration",uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6711375140756202784l,opposite="businessCollaboration",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork();
	
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetworkFor(BusinessNetwork match);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork);

}