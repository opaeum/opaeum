package org.opaeum.test.util;

import java.text.NumberFormat;

import org.opaeum.runtime.domain.AbstractFormatter;

public class ModelFormatter extends AbstractFormatter {
	static final private ThreadLocal<ModelFormatter> INSTANCE = new ThreadLocal<ModelFormatter>();


	public String formatMoneyInDefaultCurrency(Double value) {
		String result = null;
		NumberFormat format = NumberFormat.getInstance();
		result =value==null?"":format.format(value);
		return result;
	}
	
	static public ModelFormatter getInstance() {
		ModelFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new ModelFormatter());
		}
		return result;
	}
	
	public Double parseMoneyInDefaultCurrency(String value) {
		Double result = null;
		NumberFormat format = NumberFormat.getInstance();
		try {
			result = (value==null||value.length()==0?null:format.parse(value).doubleValue());
		} catch (Exception e) {
			result=null;
		}
		return result;
	}

}