package org.opaeum.eclipse.starter;

import java.util.Map.Entry;
import java.util.Set;

import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.generated.OJClassGEN;
import org.opaeum.java.metamodel.generated.OJClassifierGEN;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.metamodel.core.internal.NakedElementOwnerImpl;
import org.opaeum.textmetamodel.TextOutputNode;

public class MemoryUtil{
	public static void printMemoryUsage(){
		if(false){
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
					for(OJPathName p:OJPackage.pkgs.keySet()){
						System.out.println(p);
					}
					for(OJPathName p:OJClassGEN.pns){
						System.out.println(p);
					}
					for(Entry<Class<?>,Long> entry:NakedElementOwnerImpl.counts.entrySet()){
						System.out.println(entry.getKey().getName() + "=" + entry.getValue());
					}
				}
			}, 0);
		}
	}
}
