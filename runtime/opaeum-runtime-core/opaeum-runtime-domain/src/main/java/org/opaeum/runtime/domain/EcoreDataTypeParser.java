package org.opaeum.runtime.domain;

public class EcoreDataTypeParser{
	private static ThreadLocal<EcoreDataTypeParser> INSTANCE = new ThreadLocal<EcoreDataTypeParser>();
	public static EcoreDataTypeParser getInstance(){
		if(INSTANCE.get() == null){
			INSTANCE.set(new EcoreDataTypeParser());
		}
		return INSTANCE.get();
	}
	public String parseEString(String attribute){
		return attribute;
	}
	public boolean parseEBoolean(String attribute){
		return "true".equals(attribute);
	}
	public Boolean parseEBooleanObject(String attribute){
		try{
			return Boolean.valueOf(attribute);
		}catch(Exception e){
			return null;
		}
	}
	public Integer parseEIntegerObject(String attribute){
		try{
			return Integer.valueOf(attribute);
		}catch(Exception e){
			return null;
		}
	}
	public int parseEInt(String attribute){
		try{
			return Integer.parseInt(attribute);
		}catch(Exception e){
			return 0;
		}
	}
}
