package org.opaeum.uim.userinteractionproperties.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.cube.AxisEntry;
import org.topcased.tabbedproperties.utils.ObjectAdapter;

public class AxisEntryFilter implements IFilter{
	public boolean select(Object toTest){
		if(!(toTest instanceof Element)){
			EObject o = ObjectAdapter.adaptObject(toTest);
			return o instanceof AxisEntry;
		}
		return false;
	}
}
