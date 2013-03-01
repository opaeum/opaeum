package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface CubeQueryEditor extends EObject, UmlReference, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml);
	
	public List<CubeQuery> getQueries();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setQueries(List<CubeQuery> queries);
	
	public void setUid(String uid);

}