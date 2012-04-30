package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyValuePin;

public abstract class ManyValuePin<O> extends ValuePin<O, CollectionObjectToken<O>> implements IManyValuePin<O> {

	public ManyValuePin() {
		super();
	}

	@Override
	public abstract Collection<O> getValue();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CollectionObjectToken<O>> getInTokens() {
		return Arrays.<CollectionObjectToken<O>>asList(new CollectionObjectToken<O>(getName(), getValue()));
	}
	

	@Override
	protected int countNumberOfElementsOnTokens() {
		int size = 0;
		List<CollectionObjectToken<O>> tokens = getInTokens();
		for (CollectionObjectToken<O> collectionObjectToken : tokens) {
			size += collectionObjectToken.getElements().size();
		}
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValuePin //TODO");
		// sb.append(this.nodeStat.toString());
		return sb.toString();
	}

}
