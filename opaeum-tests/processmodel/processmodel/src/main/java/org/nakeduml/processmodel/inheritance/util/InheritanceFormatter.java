package org.opeum.processmodel.inheritance.util;

import org.opeum.runtime.domain.AbstractFormatter;

public class InheritanceFormatter extends AbstractFormatter implements IInheritanceFormatter {
	static final private ThreadLocal<InheritanceFormatter> INSTANCE = new ThreadLocal<InheritanceFormatter>();


	static public InheritanceFormatter getInstance() {
		InheritanceFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new InheritanceFormatter());
		}
		return result;
	}

}