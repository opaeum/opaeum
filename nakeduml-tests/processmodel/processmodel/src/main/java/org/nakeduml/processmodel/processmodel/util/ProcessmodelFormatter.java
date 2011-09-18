package org.nakeduml.processmodel.processmodel.util;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class ProcessmodelFormatter extends AbstractFormatter implements IProcessmodelFormatter {
	static final private ThreadLocal<ProcessmodelFormatter> INSTANCE = new ThreadLocal<ProcessmodelFormatter>();


	static public ProcessmodelFormatter getInstance() {
		ProcessmodelFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ProcessmodelFormatter());
		}
		return result;
	}

}