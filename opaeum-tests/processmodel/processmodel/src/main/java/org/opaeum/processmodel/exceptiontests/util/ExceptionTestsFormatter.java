package org.opaeum.processmodel.exceptiontests.util;

import org.opeum.runtime.domain.AbstractFormatter;

public class ExceptionTestsFormatter extends AbstractFormatter implements IExceptionTestsFormatter {
	static final private ThreadLocal<ExceptionTestsFormatter> INSTANCE = new ThreadLocal<ExceptionTestsFormatter>();


	static public ExceptionTestsFormatter getInstance() {
		ExceptionTestsFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ExceptionTestsFormatter());
		}
		return result;
	}

}