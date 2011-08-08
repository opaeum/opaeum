package org.nakeduml.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DispatchSignal {

	private BlockingQueue<Runnable> blockingQueue;
	private ExecutorService asynchPool;

	@PostConstruct
	public void startUp() {
		this.blockingQueue = new LinkedBlockingQueue<Runnable>();
		this.asynchPool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, blockingQueue, new DaemonThreadFactory("@Asynch"));
	}

	@PreDestroy
	public void shutdown() {
		this.asynchPool.shutdown();
	}

	public void submit(CdiRunnable task) {
		this.asynchPool.submit(task);
	}
}
