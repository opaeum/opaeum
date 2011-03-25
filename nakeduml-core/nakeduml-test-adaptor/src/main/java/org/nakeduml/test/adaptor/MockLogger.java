package org.nakeduml.test.adaptor;

import org.jboss.logging.Logger;

public class MockLogger extends Logger{
	protected MockLogger(){
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
		if(level==Level.ERROR){
		System.out.printf(level + ": " + loggerClassName + ": ");
		if(parameters != null && string != null){
			for(int i = 0;i < parameters.length;i++){
				Object object = parameters[i];
				if(object != null){
					string = string.replace("#" + i, object.toString());
				}else{
					string = string.replace("#" + i, "null");
				}
			}
		}
		System.out.print(string);
		System.out.println();
		if(thrown != null){
			thrown.printStackTrace();
		}else if(Level.ERROR==level){
			Thread.dumpStack();
		}
		}
	}
	@Override
	protected void doLogf(Level level,String loggerClassName,String format,Object[] parameters,Throwable thrown){
		logImpl(level, loggerClassName, parameters, thrown, format);
	}
}
