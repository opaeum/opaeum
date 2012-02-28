package org.opaeum.runtime.organization;

import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.w3c.dom.Element;

public interface IBusinessActorBase{
	public void buildTreeFromXml(Element xml,Map<String,Object> map);
	@NumlMetaInfo(uuid = "252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public IOrganizationNode getOrganization();
	@NumlMetaInfo(uuid = "252060@_X4_Mg0tyEeGElKTCe2jfDw")
	public IPersonNode getRepresentedPerson();
	public String getUid();
	public void populateReferencesFromXml(Element xml,Map<String,Object> map);
	public void setOrganization(IOrganizationNode organization);
	public void setRepresentedPerson(IPersonNode representedPerson);
	public String toXmlReferenceString();
	public String toXmlString();
}
