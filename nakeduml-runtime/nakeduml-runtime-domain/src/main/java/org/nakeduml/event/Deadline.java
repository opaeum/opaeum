package org.nakeduml.event;

import java.util.Date;

import org.drools.runtime.process.ProcessContext;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.TimeUnit;

public class Deadline extends TimeEvent{

	public Deadline(){
		super();
	}

	public Deadline(IPersistentObject target,String callBackMethodName,boolean cancelled){
		super(target, callBackMethodName, cancelled);
	}

	public Deadline(IPersistentObject target,String callBackMethodName,Date when,ProcessContext ctx){
		super(target, callBackMethodName, when, ctx);
	}

	public Deadline(IPersistentObject target,String callBackMethodName,int duration,TimeUnit timeUnit,ProcessContext ctx){
		super(target, callBackMethodName, duration, timeUnit, ctx);
	}
	public void invokeCallback(IPersistentObject context){
		try{
			//Will call the TaskInvocation
			getMethodByPersistentName(getCallbackMethodName(), Date.class).invoke(context, when);
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}

}
