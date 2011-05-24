package org.util;

import java.util.Collection;
import java.util.Set;

public interface TinkerSet<E> extends Set<E> {
	boolean tinkerAdd(E e);
	boolean tinkerAddAll(Collection<? extends E> c);
	boolean tinkerRemove(Object o);
}
