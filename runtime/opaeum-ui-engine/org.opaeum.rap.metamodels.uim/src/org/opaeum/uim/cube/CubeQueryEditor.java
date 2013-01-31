package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;

public interface CubeQueryEditor extends EObject, UmlReference, UserInterfaceRoot {
	public List<CubeQuery> getQueries();
	
	public void setQueries(List<CubeQuery> queries);

}