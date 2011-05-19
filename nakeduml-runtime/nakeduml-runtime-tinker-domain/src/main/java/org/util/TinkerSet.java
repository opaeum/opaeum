package org.util;

import java.util.Set;

public interface TinkerSet<E> extends Set<E> {
	boolean tinkerAdd(E e);
	boolean tinkerRemove(Object o);
}
