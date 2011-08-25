package org.nakeduml.eclipse;

import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import net.sf.nakeduml.feature.DefaultTransformationLog;

public class ProgressMonitorTransformationLog extends DefaultTransformationLog{
	private final Stack<IProgressMonitor> monitors = new Stack<IProgressMonitor>();
	private final Stack<Integer> sizes = new Stack<Integer>();
	public ProgressMonitorTransformationLog(IProgressMonitor monitor, int ticks){
		this.monitors.push(monitor);
		sizes.push(ticks);
	}
	@Override
	public void startTask(String name,int size){
		super.startTask(name, size);
		monitors.firstElement().setTaskName(name);
		SubProgressMonitor item = new SubProgressMonitor(monitors.peek(), sizes.peek());
		monitors.push(item);
		item.beginTask(name, sizes.peek());
		sizes.push(Math.max(1,sizes.peek()/size));
	}
	@Override
	public void endLastStep(){
		super.endLastStep();
		Integer peek = sizes.peek();
		monitors.peek().worked(peek);
	}
	@Override
	public void startStep(String step){
		super.startStep(step);
		monitors.peek().subTask(step);
	}
	@Override
	public void endLastTask(){
		super.endLastTask();
		IProgressMonitor pop = monitors.pop();
		pop.done();
		sizes.pop();
	}
	public boolean isCanceled(){
		for(IProgressMonitor m:monitors){
			if(m.isCanceled()){
				return true;
			}
		}
		return false;
	}
}