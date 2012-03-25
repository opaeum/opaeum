package org.nakeduml.runtime.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.nakeduml.runtime.domain.activity.ReplyAction;

public class TinkerAcceptCallEventBlockingQueue {

	public static TinkerAcceptCallEventBlockingQueue INSTANCE = new TinkerAcceptCallEventBlockingQueue();

	private Map<ReplyAction<?>, BlockingQueue<?>> map = Collections.synchronizedMap(new HashMap<ReplyAction<?>, BlockingQueue<?>>());

	public <T> void put(ReplyAction<T> replyAction, T value) {
		@SuppressWarnings("unchecked")
		BlockingQueue<T> blockingQueue = (BlockingQueue<T>) this.map.get(replyAction);
		if (blockingQueue != null && blockingQueue.peek() != null) {
			throw new IllegalStateException("TinkerAcceptCallEventBlockingQueue can only hold one value");
		}
		if (blockingQueue == null) {
			blockingQueue = new ArrayBlockingQueue<T>(1);
			this.map.put(replyAction, blockingQueue);
		}
		try {
			blockingQueue.put(value);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T take(ReplyAction<T> replyAction) {
		BlockingQueue<T> blockingQueue = (BlockingQueue<T>) this.map.get(replyAction);
		if (blockingQueue == null) {
			blockingQueue = new ArrayBlockingQueue<T>(1);
			this.map.put(replyAction, blockingQueue);
		}
		try {
			return (T) this.map.get(replyAction).take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
