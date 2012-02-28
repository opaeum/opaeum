package org.opaeum.runtime.organization;

import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.w3c.dom.Element;

public interface IBusinessComponent{
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	public IOrganizationNode getRepresentedOrganization();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setRepresentedOrganization(IOrganizationNode representedOrganization);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
}
