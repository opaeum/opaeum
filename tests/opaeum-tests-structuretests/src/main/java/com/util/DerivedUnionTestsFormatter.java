package com.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class DerivedUnionTestsFormatter extends AbstractFormatter implements IDerivedUnionTestsFormatter {
	static final private ThreadLocal<DerivedUnionTestsFormatter> INSTANCE = new ThreadLocal<DerivedUnionTestsFormatter>();


	static public DerivedUnionTestsFormatter getInstance() {
		DerivedUnionTestsFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new DerivedUnionTestsFormatter());
		}
		return result;
	}

}