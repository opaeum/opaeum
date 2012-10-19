package structuredbusiness;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class CityResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==370895807509634180l ) {
			result = City.JOHANNESBURG;
		} else {
			if ( i==6617031855133737322l ) {
				result = City.CAPETOWN;
			} else {
				if ( i==9013938801218636412l ) {
					result = City.PRETORIA;
				} else {
					if ( i==9203863868638345078l ) {
						result = City.DURBAN;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return structuredbusiness.City.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (City)en ) {
			case DURBAN:
				result = 9203863868638345078l;
			break;
		
			case PRETORIA:
				result = 9013938801218636412l;
			break;
		
			case CAPETOWN:
				result = 6617031855133737322l;
			break;
		
			case JOHANNESBURG:
				result = 370895807509634180l;
			break;
		
		}
		
		return result;
	}

}