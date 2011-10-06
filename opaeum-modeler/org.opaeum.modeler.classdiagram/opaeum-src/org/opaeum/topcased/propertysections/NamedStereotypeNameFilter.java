package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jface.viewers.IFilter;
import org.opaeum.emf.extraction.StereotypesHelper;

public class NamedStereotypeNameFilter implements IFilter{
	@Override
	public boolean select(Object toTest){
		return toTest instanceof DynamicEObjectImpl && StereotypesHelper.findAttribute((EObject) toTest, "name")!=null;
	}
}
