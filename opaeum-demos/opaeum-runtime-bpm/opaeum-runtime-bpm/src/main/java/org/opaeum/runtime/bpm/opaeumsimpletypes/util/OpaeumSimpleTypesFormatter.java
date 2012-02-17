package org.opaeum.runtime.bpm.opaeumsimpletypes.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class OpaeumSimpleTypesFormatter extends AbstractFormatter implements IOpaeumSimpleTypesFormatter {
	static final private ThreadLocal<OpaeumSimpleTypesFormatter> INSTANCE = new ThreadLocal<OpaeumSimpleTypesFormatter>();


	static public OpaeumSimpleTypesFormatter getInstance() {
		OpaeumSimpleTypesFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new OpaeumSimpleTypesFormatter());
		}
		return result;
	}

}