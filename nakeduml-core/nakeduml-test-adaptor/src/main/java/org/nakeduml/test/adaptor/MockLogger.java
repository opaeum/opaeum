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
		System.out.printf(level+": " +loggerClassName +": ");
		System.out.printf(message.toString(), parameters);
		System.out.println();
	}
	@Override
	protected void doLogf(Level level,String loggerClassName,String format,Object[] parameters,Throwable thrown){
		System.out.printf(level+": " +loggerClassName +": ");
		System.out.printf(format.toString(), parameters);
		System.out.println();
	}
}
