package org.opaeum.processmodel.humancentric.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class HumanCentricFormatter extends AbstractFormatter implements IHumanCentricFormatter {
	static final private ThreadLocal<HumanCentricFormatter> INSTANCE = new ThreadLocal<HumanCentricFormatter>();


	static public HumanCentricFormatter getInstance() {
		HumanCentricFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new HumanCentricFormatter());
		}
		return result;
	}

}