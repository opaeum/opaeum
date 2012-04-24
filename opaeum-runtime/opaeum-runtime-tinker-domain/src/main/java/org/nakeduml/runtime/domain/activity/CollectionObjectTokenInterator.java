package org.nakeduml.runtime.domain.activity;

import java.util.Collection;
import java.util.Iterator;

public class CollectionObjectTokenInterator<T> extends ObjectTokenInterator<T, CollectionObjectToken<T>> {

	private Iterator<T> iterator;
	private String name;

	public CollectionObjectTokenInterator(String name, Iterator<T> iterator) {
		super(name, iterator);
		this.iterator = iterator;
		this.name = name;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public CollectionObjectToken<T> next() {
		return new CollectionObjectToken<T>(name, (Collection<T>) this.iterator.next());
	}

}
