package org.opeum.runtime.bpm.util;

import org.opeum.runtime.domain.AbstractFormatter;

public class OpiumLibraryForBPMFormatter extends AbstractFormatter implements IOpiumLibraryForBPMFormatter {
	static final private ThreadLocal<OpiumLibraryForBPMFormatter> INSTANCE = new ThreadLocal<OpiumLibraryForBPMFormatter>();


	static public OpiumLibraryForBPMFormatter getInstance() {
		OpiumLibraryForBPMFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OpiumLibraryForBPMFormatter());
		}
		return result;
	}

}