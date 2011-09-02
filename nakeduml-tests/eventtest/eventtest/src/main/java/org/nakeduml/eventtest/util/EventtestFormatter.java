package org.nakeduml.eventtest.util;

import org.nakeduml.runtime.domain.AbstractFormatter;

public class EventtestFormatter extends AbstractFormatter implements IEventtestFormatter {
	static final private ThreadLocal<EventtestFormatter> INSTANCE = new ThreadLocal<EventtestFormatter>();


	static public EventtestFormatter getInstance() {
		EventtestFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new EventtestFormatter());
		}
		return result;
	}

}