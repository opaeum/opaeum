package org.opaeum.processmodel.exceptionexpansion.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class ExceptionExpansionFormatter extends AbstractFormatter implements IExceptionExpansionFormatter {
	static final private ThreadLocal<ExceptionExpansionFormatter> INSTANCE = new ThreadLocal<ExceptionExpansionFormatter>();


	static public ExceptionExpansionFormatter getInstance() {
		ExceptionExpansionFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ExceptionExpansionFormatter());
		}
		return result;
	}

}