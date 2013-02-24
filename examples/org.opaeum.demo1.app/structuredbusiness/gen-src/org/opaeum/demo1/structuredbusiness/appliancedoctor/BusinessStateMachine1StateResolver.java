package org.opaeum.demo1.structuredbusiness.appliancedoctor;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class BusinessStateMachine1StateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==7018546131653328789l ) {
			result = BusinessStateMachine1State.STATE0;
		} else {
			if ( i==8578110679676129975l ) {
				result = BusinessStateMachine1State.STATE1;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1State.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (BusinessStateMachine1State)en ) {
			case STATE1:
				result = 8578110679676129975l;
			break;
		
			case STATE0:
				result = 7018546131653328789l;
			break;
		
		}
		
		return result;
	}

}