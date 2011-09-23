package org.nakeduml.processmodel.acceptcallactiontests.util;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class AcceptCallActionTestsFormatter extends AbstractFormatter implements IAcceptCallActionTestsFormatter {
	static final private ThreadLocal<AcceptCallActionTestsFormatter> INSTANCE = new ThreadLocal<AcceptCallActionTestsFormatter>();


	static public AcceptCallActionTestsFormatter getInstance() {
		AcceptCallActionTestsFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new AcceptCallActionTestsFormatter());
		}
		return result;
	}

}