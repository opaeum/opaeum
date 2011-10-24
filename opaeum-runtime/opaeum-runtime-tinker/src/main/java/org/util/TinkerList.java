package org.util;

import java.util.List;

public interface TinkerList<E> extends List<E> {
	boolean tinkerAdd(E e);
	boolean tinkerRemove(Object o);
}
