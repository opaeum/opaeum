package org.nakeduml.runtime.domain.activity;

import java.util.Iterator;

public abstract class ObjectTokenInterator<T, TOKEN extends ObjectToken<T>> implements Iterator<ObjectToken<T>> {

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
	public abstract TOKEN next();

	@Override
	public void remove() {
		this.iterator.remove();
	}

}
