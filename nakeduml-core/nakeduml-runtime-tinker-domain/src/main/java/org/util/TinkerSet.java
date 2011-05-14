package org.util;

import java.util.Set;

import org.nakeduml.runtime.domain.TinkerNode;

public interface TinkerSet<E> extends Set<E> {
	boolean tinkerAdd(E e);
	boolean tinkerRemove(TinkerNode e);
}
