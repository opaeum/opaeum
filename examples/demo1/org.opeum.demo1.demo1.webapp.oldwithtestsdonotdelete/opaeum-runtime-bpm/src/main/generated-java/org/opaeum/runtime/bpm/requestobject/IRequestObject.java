package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_Wd2QoI53EeCfQedkc0TCdA")
public interface IRequestObject extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setRequest(AbstractRequest request);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToRequest(AbstractRequest val);
	
	public void z_internalRemoveFromRequest(AbstractRequest val);

}