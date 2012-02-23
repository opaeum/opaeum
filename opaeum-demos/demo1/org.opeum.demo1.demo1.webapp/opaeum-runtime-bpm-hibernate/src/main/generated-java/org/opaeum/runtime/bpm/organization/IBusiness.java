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

@NumlMetaInfo(uuid="252060@_G6MA0FYtEeGj5_I7bIwNoA")
public interface IBusiness extends HibernateEntity, CompositionNode, Serializable, IBusinessComponent, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration();
	
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public IBusinessCollaboration_iBusinessComponent_1 getIBusinessCollaboration_iBusinessComponent_1_businessCollaboration();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBusinessCollaboration(IBusinessCollaboration businessCollaboration);
	
	public void setIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(IBusinessCollaboration_iBusinessComponent_1 iBusinessCollaboration_iBusinessComponent_1_businessCollaboration);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration val);
	
	public void z_internalAddToIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(IBusinessCollaboration_iBusinessComponent_1 val);
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration val);
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(IBusinessCollaboration_iBusinessComponent_1 val);

}