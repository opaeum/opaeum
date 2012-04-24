package org.nakeduml.runtime.domain.activity;

import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyInputPin;

public abstract class ManyValuePin<O> extends ValuePin<O, CollectionObjectToken<O>> implements IManyInputPin<O> {

	public ManyValuePin() {
		super();
	}

	@Override
	public abstract Collection<O> getValue();

	@Override
	protected int countNumberOfElementsOnTokens() {
		int size = 0;
		List<CollectionObjectToken<O>> tokens = getInTokens();
		for (CollectionObjectToken<O> collectionObjectToken : tokens) {
			size += collectionObjectToken.getCollection().size();
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
