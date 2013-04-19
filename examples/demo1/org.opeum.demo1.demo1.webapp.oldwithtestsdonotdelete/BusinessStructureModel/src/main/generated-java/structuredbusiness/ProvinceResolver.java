package structuredbusiness;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class ProvinceResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==360849098396972512l ) {
			result = Province.GAUTENG;
		} else {
			if ( i==4498978506497688454l ) {
				result = Province.WESTERNCAPE;
			} else {
				if ( i==1197396414679960874l ) {
					result = Province.KWAZULUNATAL;
				} else {
					if ( i==3058547728091853860l ) {
						result = Province.MPUMALANGA;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return structuredbusiness.Province.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Province)en ) {
			case MPUMALANGA:
				result = 3058547728091853860l;
			break;
		
			case KWAZULUNATAL:
				result = 1197396414679960874l;
			break;
		
			case WESTERNCAPE:
				result = 4498978506497688454l;
			break;
		
			case GAUTENG:
				result = 360849098396972512l;
			break;
		
		}
		
		return result;
	}

}