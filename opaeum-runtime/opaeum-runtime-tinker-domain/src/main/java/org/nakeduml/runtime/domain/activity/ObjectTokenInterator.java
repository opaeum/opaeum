package org.nakeduml.runtime.domain.activity;

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

	@Override
	public ObjectToken<T> next() {
		return new ObjectToken<T>(name, this.iterator.next());
	}

	@Override
	public void remove() {
		this.iterator.remove();
	}

}
