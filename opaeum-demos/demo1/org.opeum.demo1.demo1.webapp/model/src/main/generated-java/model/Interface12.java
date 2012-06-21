package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="model.uml@_fvMwELkpEeGPU4u_bLx6fA")
public interface Interface12 extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	@NumlMetaInfo(uuid="model.uml@_pl7T4LlFEeG-Ou4fV0X62w")
	public Integer Operation1(@ParameterMetaInfo(name="param1",opaeumId=2312122819401591160l,uuid="model.uml@_A2bl8LlGEeG-Ou4fV0X62w") Integer param1);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}