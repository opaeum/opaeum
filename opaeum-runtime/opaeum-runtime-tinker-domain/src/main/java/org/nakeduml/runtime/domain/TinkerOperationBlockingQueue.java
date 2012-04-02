package org.nakeduml.runtime.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TinkerOperationBlockingQueue {

	public static TinkerOperationBlockingQueue INSTANCE = new TinkerOperationBlockingQueue();

	private Map<String, BlockingQueue<?>> map = Collections.synchronizedMap(new HashMap<String, BlockingQueue<?>>());

	public <T> void put(String uid, T value) {
		@SuppressWarnings("unchecked")
		BlockingQueue<List<T>> blockingQueue = (BlockingQueue<List<T>>) this.map.get(uid);
		if (blockingQueue != null && blockingQueue.peek() != null) {
			throw new IllegalStateException("TinkerAcceptCallEventBlockingQueue can only hold one value");
		} else {
			blockingQueue = ensureInitialized(uid);
		}
		blockingQueue.peek().add(value);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> take(String uid) {
		ensureInitialized(uid);
		try {
			return (List<T>)this.map.get(uid).take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private <T> BlockingQueue<List<T>> ensureInitialized(String uid) {
		@SuppressWarnings("unchecked")
		BlockingQueue<List<T>> blockingQueue = (BlockingQueue<List<T>>) this.map.get(uid);
		if (blockingQueue == null) {
			blockingQueue = new ArrayBlockingQueue<List<T>>(1);
			try {
				blockingQueue.put(new ArrayList<T>());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			this.map.put(uid, blockingQueue);
		}
		return blockingQueue;
	}
}
