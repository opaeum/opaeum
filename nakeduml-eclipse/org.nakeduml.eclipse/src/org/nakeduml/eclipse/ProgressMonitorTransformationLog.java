package org.nakeduml.eclipse;

import org.eclipse.core.runtime.IProgressMonitor;
import net.sf.nakeduml.feature.TransformationProcess;

public class ProgressMonitorTransformationLog implements TransformationProcess.TransformationLog{
	private final IProgressMonitor monitor;
	private String lasTask;
	public ProgressMonitorTransformationLog(IProgressMonitor monitor){
		this.monitor = monitor;
	}
	@Override
	public void startTask(String s){
		this.lasTask=s;
		System.out.println(s);
		monitor.subTask(s);
	}
	@Override
	public void endTask(){
		monitor.worked(1);
	}
}