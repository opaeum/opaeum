package org.opaeum.emf.workspace;

import java.util.Comparator;

import org.eclipse.uml2.uml.Element;

public class DefaultOpaeumComparator implements Comparator<Element>{
	@Override
	public int compare(Element o1,Element o2){
		return EmfWorkspace.getId(o1).compareTo(EmfWorkspace.getId(o2));
	}
}
