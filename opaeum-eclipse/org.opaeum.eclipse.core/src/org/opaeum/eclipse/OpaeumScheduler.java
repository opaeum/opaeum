package org.opaeum.eclipse;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class OpaeumScheduler{
	private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
	public static void sheduleTask(Runnable r,long l){
		threadPool.schedule(r, l, TimeUnit.MILLISECONDS);
	}
	public static void schedule(Runnable task,long delay){
		threadPool.schedule(task, delay, TimeUnit.MILLISECONDS);
	}

}