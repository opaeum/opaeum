package model.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class ModelFormatter extends AbstractFormatter implements IModelFormatter {
	static final private ThreadLocal<ModelFormatter> INSTANCE = new ThreadLocal<ModelFormatter>();


	static public ModelFormatter getInstance() {
		ModelFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ModelFormatter());
		}
		return result;
	}

}