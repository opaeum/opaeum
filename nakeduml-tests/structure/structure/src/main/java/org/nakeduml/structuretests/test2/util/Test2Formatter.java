package org.nakeduml.structuretests.test2.util;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class Test2Formatter extends AbstractFormatter implements ITest2Formatter {
	static final private ThreadLocal<Test2Formatter> INSTANCE = new ThreadLocal<Test2Formatter>();


	static public Test2Formatter getInstance() {
		Test2Formatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new Test2Formatter());
		}
		return result;
	}

}