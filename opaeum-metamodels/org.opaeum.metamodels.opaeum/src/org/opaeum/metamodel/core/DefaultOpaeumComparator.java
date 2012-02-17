package org.opaeum.metamodel.core;

import java.util.Comparator;

public class DefaultOpaeumComparator implements Comparator<INakedElement>{
	@Override
	public int compare(INakedElement o1,INakedElement o2){
		return o1.getMappingInfo().getIdInModel().compareTo(o2.getMappingInfo().getIdInModel());
	}
}
