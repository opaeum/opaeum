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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6783615175887897848l,opposite="spouse",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_wqZp8IjPEeKq68owPnlvHg@Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	public Marriage getMarriage_surnameProvider();
	
	public Marriage getMarriage_surnameProviderFor(SurnameProvider match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2792093672067248130l,opposite="spouse",uuid="Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
	public SurnameProvider getSurnameProvider();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setSurnameProvider(SurnameProvider surnameProvider);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToMarriage_surnameProvider(Marriage marriage_surnameProvider);
	
	public void z_internalRemoveFromMarriage_surnameProvider(Marriage marriage_surnameProvider);

}