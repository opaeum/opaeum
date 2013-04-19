package org.opaeum.runtime.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

public class TextStrategyFactory extends SimpleTypeRuntimeStrategyFactory{
	public static class MyFromStringStrategy implements FromStringConverter{
		@Override
		public Object fromString(String val) throws ParseException {
			return val;
		}
	}
	public TextStrategyFactory() {
		super("",String.class,MyFromStringStrategy.class);
	}
}
