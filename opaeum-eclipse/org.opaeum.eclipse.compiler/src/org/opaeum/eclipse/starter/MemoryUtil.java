package org.opaeum.eclipse.starter;

import java.util.Map.Entry;

import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextOutputNode;

public class MemoryUtil{
	@SuppressWarnings("unused")
	public static void printMemoryUsage(){
		if(true){
			EmfToOpaeumSynchronizer.schedule(new Runnable(){
				@Override
				public void run(){
					System.gc();
					try{
						Thread.sleep(5000);
					}catch(InterruptedException e){
					}
					System.out.println("Max Memory:" + Runtime.getRuntime().maxMemory());
					System.out.println("Total Memory:" + Runtime.getRuntime().totalMemory());
					System.out.println("Free Memory:" + Runtime.getRuntime().freeMemory());
					for(Entry<Class<?>,Long> entry:TextOutputNode.counts.entrySet()){
						// System.out.println(entry.getKey().getName() + "=" + entry.getValue());
					}
					for(Entry<Class<?>,Long> entry:OJElementGEN.counts.entrySet()){
						System.out.println(entry.getKey().getName() + "=" + entry.getValue());
					}
					System.out.println("OJUtil.instanceCount:"+OJUtil.instanceCount);
	
				}
			}, 0);
		}
	}
}
