package org.nakeduml.test.adaptor;

import org.jboss.logging.Logger;

public class CditTestLogger extends Logger{
	public static boolean DUMP_STACK_ON_ERROR = false;
	protected CditTestLogger(){
		super("test");
	}
	@Override
	public boolean isEnabled(Level level){
		return true;
	}
	@Override
	protected void doLog(Level level,String loggerClassName,Object message,Object[] parameters,Throwable thrown){
		String string = message.toString();
		logImpl(level, loggerClassName, parameters, thrown, string);
	}
	private void logImpl(Level level,String loggerClassName,Object[] parameters,Throwable thrown,String string){
		System.out.printf(level + ": " + loggerClassName + ": ");
		if(parameters != null && string != null){
			string = String.format(string, parameters);
		}
		System.out.print(string);
		System.out.println();
		if(DUMP_STACK_ON_ERROR){
			if(thrown != null){
				thrown.printStackTrace();
			}else if(Level.ERROR == level){
				Thread.dumpStack();
			}
		}
	}
	@Override
	protected void doLogf(Level level,String loggerClassName,String format,Object[] parameters,Throwable thrown){
		logImpl(level, loggerClassName, parameters, thrown, format);
	}
}
