package org.opaeum.uim.cube;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;

public interface CubeQueryEditor extends EObject, UmlReference, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<CubeQuery> getQueries();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setQueries(List<CubeQuery> queries);
	
	public void setUid(String uid);

}