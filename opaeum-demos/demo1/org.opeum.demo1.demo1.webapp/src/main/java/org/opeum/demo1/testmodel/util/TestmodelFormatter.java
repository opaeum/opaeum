package org.opeum.demo1.testmodel.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class TestmodelFormatter extends AbstractFormatter implements ITestmodelFormatter {
	static final private ThreadLocal<TestmodelFormatter> INSTANCE = new ThreadLocal<TestmodelFormatter>();


	static public TestmodelFormatter getInstance() {
		TestmodelFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new TestmodelFormatter());
		}
		return result;
	}

}