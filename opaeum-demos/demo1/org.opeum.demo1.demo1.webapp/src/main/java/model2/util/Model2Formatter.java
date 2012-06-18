package model2.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class Model2Formatter extends AbstractFormatter implements IModel2Formatter {
	static final private ThreadLocal<Model2Formatter> INSTANCE = new ThreadLocal<Model2Formatter>();


	static public Model2Formatter getInstance() {
		Model2Formatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new Model2Formatter());
		}
		return result;
	}

}