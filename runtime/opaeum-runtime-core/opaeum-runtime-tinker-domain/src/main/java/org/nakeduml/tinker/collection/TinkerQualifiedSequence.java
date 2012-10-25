package org.nakeduml.tinker.collection;

import java.util.List;

public interface TinkerQualifiedSequence<E> extends TinkerSequence<E> {
	public boolean add(E e, List<Qualifier> qualifiers);
	public void add(int indexOf, E e, List<Qualifier> qualifiers);
}
