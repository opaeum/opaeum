package ocltests.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class OcltestsFormatter extends AbstractFormatter implements IOcltestsFormatter {
	static final private ThreadLocal<OcltestsFormatter> INSTANCE = new ThreadLocal<OcltestsFormatter>();


	static public OcltestsFormatter getInstance() {
		OcltestsFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OcltestsFormatter());
		}
		return result;
	}

}