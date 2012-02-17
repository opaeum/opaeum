package org.opaeum.organization.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class OpaeumLibraryForBPMFormatter extends AbstractFormatter implements IOpaeumLibraryForBPMFormatter {
	static final private ThreadLocal<OpaeumLibraryForBPMFormatter> INSTANCE = new ThreadLocal<OpaeumLibraryForBPMFormatter>();


	static public OpaeumLibraryForBPMFormatter getInstance() {
		OpaeumLibraryForBPMFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OpaeumLibraryForBPMFormatter());
		}
		return result;
	}

}