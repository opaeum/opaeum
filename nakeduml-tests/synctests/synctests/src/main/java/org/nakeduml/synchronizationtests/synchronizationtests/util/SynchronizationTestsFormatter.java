package org.nakeduml.synchronizationtests.synchronizationtests.util;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class SynchronizationTestsFormatter extends AbstractFormatter implements ISynchronizationTestsFormatter {
	static final private ThreadLocal<SynchronizationTestsFormatter> INSTANCE = new ThreadLocal<SynchronizationTestsFormatter>();


	static public SynchronizationTestsFormatter getInstance() {
		SynchronizationTestsFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new SynchronizationTestsFormatter());
		}
		return result;
	}

}