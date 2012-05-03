package structuredbusiness;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class ApplianceTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==7669130221086869555l ) {
			result = ApplianceType.DISHWASHER;
		} else {
			if ( i==1021599685950688687l ) {
				result = ApplianceType.TUMBLEDRYER;
			} else {
				if ( i==1858247726865489363l ) {
					result = ApplianceType.WASHINGMACHINE;
				} else {
				
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return structuredbusiness.ApplianceType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (ApplianceType)en ) {
			case WASHINGMACHINE:
				result = 1858247726865489363l;
			break;
		
			case TUMBLEDRYER:
				result = 1021599685950688687l;
			break;
		
			case DISHWASHER:
				result = 7669130221086869555l;
			break;
		
		}
		
		return result;
	}

}