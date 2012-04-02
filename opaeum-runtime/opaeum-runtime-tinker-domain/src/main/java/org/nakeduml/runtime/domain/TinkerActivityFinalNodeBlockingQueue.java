package org.nakeduml.runtime.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TinkerActivityFinalNodeBlockingQueue {

	public static TinkerActivityFinalNodeBlockingQueue INSTANCE = new TinkerActivityFinalNodeBlockingQueue();

	private Map<String, BlockingQueue<Boolean>> map = Collections.synchronizedMap(new HashMap<String, BlockingQueue<Boolean>>());

	public void complete(String uid) {
		BlockingQueue<Boolean> blockingQueue = (BlockingQueue<Boolean>) this.map.get(uid);
		if (blockingQueue != null && blockingQueue.peek() != null) {
			throw new IllegalStateException("TinkerAcceptCallEventBlockingQueue can only hold one value");
		} else {
			blockingQueue = ensureInitialized(uid);
		}
		try {
			blockingQueue.put(true);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Boolean isComplete(String uid) {
		ensureInitialized(uid);
		try {
			return this.map.get(uid).take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private BlockingQueue<Boolean> ensureInitialized(String uid) {
		BlockingQueue<Boolean> blockingQueue = this.map.get(uid);
		if (blockingQueue == null) {
			blockingQueue = new ArrayBlockingQueue<Boolean>(1);
			this.map.put(uid, blockingQueue);
		}
		return blockingQueue;
	}
}
