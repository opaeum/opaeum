package org.opaeum.processmodel.expansion.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class ExpansionFormatter extends AbstractFormatter implements IExpansionFormatter {
	static final private ThreadLocal<ExpansionFormatter> INSTANCE = new ThreadLocal<ExpansionFormatter>();


	static public ExpansionFormatter getInstance() {
		ExpansionFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ExpansionFormatter());
		}
		return result;
	}

}