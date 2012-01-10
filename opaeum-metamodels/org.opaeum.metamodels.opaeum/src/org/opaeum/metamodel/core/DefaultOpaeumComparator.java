package org.opaeum.metamodel.core;

import java.util.Comparator;

import org.opaeum.metamodel.core.internal.NakedImportedElementImpl;

public class DefaultOpaeumComparator implements Comparator<INakedElement>{

	@Override
	public int compare(INakedElement o1,INakedElement o2){
		int compareTo = o1.getMappingInfo().getIdInModel().compareTo(o2.getMappingInfo().getIdInModel());
		if(compareTo==0 && o1!=o2 && !(o1 instanceof NakedImportedElementImpl)){
			System.out.println();
		}
		return compareTo;
	}
}
