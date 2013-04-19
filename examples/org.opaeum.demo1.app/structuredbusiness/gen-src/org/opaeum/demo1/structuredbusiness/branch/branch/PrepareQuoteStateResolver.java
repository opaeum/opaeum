package org.opaeum.demo1.structuredbusiness.branch.branch;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class PrepareQuoteStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==836851331648963919l ) {
			result = PrepareQuoteState.ESCALATED;
		} else {
			if ( i==2638769176053329929l ) {
				result = PrepareQuoteState.DRAFT;
			} else {
				if ( i==8036718979494311659l ) {
					result = PrepareQuoteState.SUBMITTED;
				} else {
					if ( i==6554627263207717015l ) {
						result = PrepareQuoteState.APPROVED;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteState.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (PrepareQuoteState)en ) {
			case APPROVED:
				result = 6554627263207717015l;
			break;
		
			case SUBMITTED:
				result = 8036718979494311659l;
			break;
		
			case DRAFT:
				result = 2638769176053329929l;
			break;
		
			case ESCALATED:
				result = 836851331648963919l;
			break;
		
		}
		
		return result;
	}

}