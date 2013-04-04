package org.opaeum.eclipse.starter;

import java.util.Map.Entry;

import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumScheduler;
import org.opaeum.emf.extraction.AbstractEmfPhase;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.visitor.TextFileGeneratingVisitor;

public class MemoryUtil{
	public static void printMemoryUsage(){
		if(true){
			OpaeumScheduler.schedule(new Runnable(){
				@Override
				public void run(){
					System.gc();
					try{
						Thread.sleep(5000);
					}catch(InterruptedException e){
					}
					OpaeumEclipsePlugin.logInfo("Max Memory:" + Runtime.getRuntime().maxMemory());
					OpaeumEclipsePlugin.logInfo("Total Memory:" + Runtime.getRuntime().totalMemory());
					OpaeumEclipsePlugin.logInfo("Free Memory:" + Runtime.getRuntime().freeMemory());
					for(Entry<Class<?>,Long> entry:TextOutputNode.counts.entrySet()){
						 OpaeumEclipsePlugin.logInfo(entry.getKey().getName() + "=" + entry.getValue());
					}
					for(Entry<Class<?>,Long> entry:OJElementGEN.counts.entrySet()){
						OpaeumEclipsePlugin.logInfo(entry.getKey().getName() + "=" + entry.getValue());
					}
					for(Entry<Class<?>,Integer> entry:TextFileGeneratingVisitor.counts.entrySet()){
						OpaeumEclipsePlugin.logInfo(entry.getKey().getName() + "=" + entry.getValue());
					}
					for(Entry<Class<?>,Integer> entry:AbstractEmfPhase.counts.entrySet()){
						OpaeumEclipsePlugin.logInfo(entry.getKey().getName() + "=" + entry.getValue());
					}
					OpaeumEclipsePlugin.logInfo("OJUtil.instanceCount:"+OJUtil.instanceCount);
					OpaeumEclipsePlugin.logInfo("EmfWorkspace.instanceCount:"+EmfWorkspace.instanceCount);
	
				}
			}, 0);
		}
	}
}
