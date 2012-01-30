package org.nakeduml.runtime.domain;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TinkerClassifierBehaviorExecutorService {

	public static TinkerClassifierBehaviorExecutorService INSTANCE = new TinkerClassifierBehaviorExecutorService();
	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(executorService);
	
	private TinkerClassifierBehaviorExecutorService() {
		super();
	}
	
	public void shutdown() {
		executorService.shutdown();
	}
	
	public void submit(IClassifierSignalEvent cse) {
		completionService.submit(cse);
	}

	public Future<Boolean> take() {
		try {
			return this.completionService.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void waitForCompletion(long timeout, TimeUnit unit) {
		try {
			this.executorService.awaitTermination(timeout, unit);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
