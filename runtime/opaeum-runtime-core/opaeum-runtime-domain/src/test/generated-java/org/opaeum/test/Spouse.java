package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_wqZp8IjPEeKq68owPnlvHg")
public interface Spouse extends CompositionNode, Serializable {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7521053949158238812l,opposite="spouse",uuid="Structures.uml@_0-KlNIjPEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_0-KlNIjPEeKq68owPnlvHg")
	public SurnameProvider getSurnameProvider();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setSurnameProvider(SurnameProvider surnameProvider);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider);
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider);

}