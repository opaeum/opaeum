package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.binding.LookupBinding;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimLookup extends EObject, UimControl {
	public void buildTreeFromXml(Element xml);
	
	public LookupBinding getLookupSource();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setLookupSource(LookupBinding lookupSource);
	
	public void setUid(String uid);

}