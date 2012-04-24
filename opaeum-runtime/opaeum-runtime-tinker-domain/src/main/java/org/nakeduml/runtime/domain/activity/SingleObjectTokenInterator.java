package org.nakeduml.runtime.domain.activity;

import java.util.Iterator;

public class SingleObjectTokenInterator<T> implements Iterator<SingleObjectToken<T>> {

	private Iterator<T> iterator;
	private String name;
	
	public SingleObjectTokenInterator(String name, Iterator<T> iterator) {
		super();
		this.iterator = iterator;
		this.name = name;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public SingleObjectToken<T> next() {
		return new SingleObjectToken<T>(name, this.iterator.next());
	}

	@Override
	public void remove() {
		this.iterator.remove();
	}

}
