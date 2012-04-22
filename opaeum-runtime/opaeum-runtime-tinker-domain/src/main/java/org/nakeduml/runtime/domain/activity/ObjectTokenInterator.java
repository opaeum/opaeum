package org.nakeduml.runtime.domain.activity;

import java.util.Collection;
import java.util.Iterator;

public class ObjectTokenInterator<T> implements Iterator<ObjectToken<T>> {

	private Iterator<T> iterator;
	private String name;
	
	public ObjectTokenInterator(String name, Iterator<T> iterator) {
		super();
		this.iterator = iterator;
		this.name = name;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ObjectToken<T> next() {
		T o = this.iterator.next();
		if (o instanceof  Collection) {
			return new CollectionObjectToken<T>(name, (Collection<T>) o);
		} else {
			return new SingleObjectToken<T>(name, o);
		}
	}

	@Override
	public void remove() {
		this.iterator.remove();
	}

}
