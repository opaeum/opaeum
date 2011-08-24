package org.nakeduml.eclipse;

import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import net.sf.nakeduml.feature.TransformationProcess;

public class ProgressMonitorTransformationLog implements TransformationProcess.TransformationProgressLog{
	private final Stack<IProgressMonitor> monitors = new Stack<IProgressMonitor>();
	private final Stack<Long> startTimes = new Stack<Long>();
	private final Stack<String> taskNames = new Stack<String>();
	public ProgressMonitorTransformationLog(IProgressMonitor monitor, int ticks){
		this.monitors.push(new SubProgressMonitor(monitor, ticks));
	}
	@Override
	public void startTask(String name,int size){
		if(monitors.size()>2){
			System.out.println();
		}
		monitors.firstElement().setTaskName(name);
		SubProgressMonitor item = new SubProgressMonitor(monitors.peek(), size);
		item.beginTask(name, size);
		monitors.push(item);
		taskNames.push(name);
		startTimes.push(System.currentTimeMillis());
	}
	@Override
	public void workOnStep(String step){
		monitors.peek().subTask(step);
		monitors.peek().worked(1);
		System.out.println(step);
	}
	@Override
	public void endTask(){
		IProgressMonitor pop = monitors.pop();
		System.out.println(taskNames.pop() + " took " + (System.currentTimeMillis() - startTimes.pop()) + " ms");
		pop.done();
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