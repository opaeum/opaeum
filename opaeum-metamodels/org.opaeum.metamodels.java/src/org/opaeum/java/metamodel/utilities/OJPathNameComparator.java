package org.opaeum.java.metamodel.utilities;

import java.util.Comparator;

import org.opaeum.java.metamodel.OJPathName;

public class OJPathNameComparator implements Comparator<OJPathName>{
	@Override
	public int compare(OJPathName o1,OJPathName o2){
		return o1.toString().compareTo(o2.toString());
	}
}
