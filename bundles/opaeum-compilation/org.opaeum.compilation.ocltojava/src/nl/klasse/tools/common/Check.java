package nl.klasse.tools.common;

public class Check{
	static public boolean ENABLED = true;
	public static void pre(String message,boolean condition){
		if(Check.ENABLED && !condition){
			throw new RuntimeException("precondition failed in " + message);
		}
	}
	public static void post(String message,boolean condition){
		if(Check.ENABLED && !condition){
			throw new RuntimeException("postcondition failed in " + message);
		}
	}
	public static void isTrue(String message,boolean condition){
		if(Check.ENABLED && !condition){
			throw new RuntimeException("check failed in " + message);
		}
	}
}
