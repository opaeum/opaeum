package org.util;

import java.util.Collection;

public interface TinkerCollection<E> extends Collection<E> {
	boolean tinkerAdd(E e);
	boolean tinkerRemove(Object o);
}
