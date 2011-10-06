package org.opaeum.feature;

import java.util.Stack;

import org.opaeum.feature.TransformationProcess.TransformationProgressLog;

public class DefaultTransformationLog implements TransformationProgressLog{
	protected final Stack<Long> taskStartTimes = new Stack<Long>();
	protected final Stack<Long> stepStartTimes = new Stack<Long>();
	protected final Stack<String> taskNames = new Stack<String>();
	protected final Stack<String> stepNames = new Stack<String>();
	@Override
	public void startTask(String name,int size){
		taskNames.push(name);
		taskStartTimes.push(System.currentTimeMillis());
	}
	@Override
	public void startStep(String step){
		System.out.println(step);
		stepNames.push(step);
		stepStartTimes.push(System.currentTimeMillis());
	}
	@Override
	public void endLastTask(){
		System.out.println(taskNames.pop() + " took " + (System.currentTimeMillis() - taskStartTimes.pop()) + " ms");
	}
	@Override
	public boolean isCanceled(){
		return false;
	}
	@Override
	public void endLastStep(){
		System.out.println(stepNames.pop() + " took " + (System.currentTimeMillis() - stepStartTimes.pop()) + " ms");
		
	}
	@Override
	public void error(String string,Throwable t){
		System.err.println(string);
		t.printStackTrace();
	}
	@Override
	public void info(String string){
		System.out.println(string);
		
	}
}