package org.opaeum.rap.runtime.editingsupport;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class GenericConverter{
	static ThreadLocal<GenericConverter> instance = new ThreadLocal<GenericConverter>();
	NumberFormat decimalFormat = new DecimalFormat("#0.0#");
	NumberFormat integerFormat = new DecimalFormat("#0");
	public static GenericConverter getInstance(){
		if(instance.get() == null){
			instance.set(new GenericConverter());
		}
		return instance.get();
	}
	public Object fromString(Class<?> t,String value) throws ParseException{
		if(value == null){
			return null;
		}else if(t == Integer.class){
			return ((Number)integerFormat.parseObject(value)).intValue();
		}else if(t == Double.class){
			return ((Number)decimalFormat.parseObject(value)).doubleValue();
		}else if(t == Boolean.class){
			// Highly unlikely
			return Boolean.valueOf(value);
		}else if(t == String.class){
			return value;
		}else{
			throw new IllegalArgumentException("No default format strategy for " + t.getSimpleName());
		}
	}
	public String toString(Object value){
		if(value == null){
			return "";
		}else if(value.getClass() == Integer.class){
			return integerFormat.format(value);
		}else if(value.getClass() == Double.class){
			return decimalFormat.format(value);
		}else{
			return value.toString();
		}
	}
}
