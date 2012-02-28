package org.opaeum.runtime.organization;

import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.w3c.dom.Element;

public interface IBusinessCollaborationBase{
	public void buildTreeFromXml(Element xml,Map<String,Object> map);
	@NumlMetaInfo(uuid = "252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<? extends IBusinessActorBase> getBusinessActor();
	@NumlMetaInfo(uuid = "252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<? extends IBusinessBase> getBusinessComponent();
	@NumlMetaInfo(uuid = "252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public IBusinessNetwork getBusinessNetwork();
	public String getUid();
	public void populateReferencesFromXml(Element xml,Map<String,Object> map);
	public String toXmlReferenceString();
	public String toXmlString();
}
