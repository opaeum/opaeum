package org.opaeum.eclipse;

import java.util.Comparator;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;

public class ElementComparator implements Comparator<Element>{

	@Override
	public int compare(Element o1,Element o2){
		String id2 = EmfWorkspace.getId(o2);
		String id1 = EmfWorkspace.getId(o1);
		if(id1==null){
			if(id1==null){
				return 0;
			}else{
				return -1;
			}
		}else {
			if(id2==null){
				return 1;
			}
		}
		return id1.compareTo(id2);
	}
}
