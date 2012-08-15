package org.opaeum.runtime.strategy;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class IntegerStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class IntegerToStringConversionStrategy implements ToStringConverter{
		@Override
		public String toString(Object val){
			if(val == null){
				return "";
			}else{
				return val.toString();
			}
		}
	}
	public static class StringToIntegerConversionStrategy implements FromStringConverter{
		@Override
		public Integer fromString(String val){
			return Integer.valueOf(val);
		}
	}
	@SuppressWarnings("unchecked")
	public IntegerStrategyFactory(){
		super(Integer.class, IntegerToStringConversionStrategy.class,StringToIntegerConversionStrategy.class);
	}
}
