package org.nakeduml.tinker.collection;

import org.nakeduml.runtime.domain.TinkerCompositionNode;

public class TinkerBagImpl<E> extends BaseBag<E> implements TinkerBag<E> {

	public TinkerBagImpl(TinkerCompositionNode owner, String label, boolean isInverse, boolean isManyToMany) {
		super();
		this.owner = owner;
		this.vertex = owner.getVertex();
		this.label = label;
		this.parentClass = owner.getClass();
		this.inverse = isInverse;
		this.manyToMany = isManyToMany;
	}

	@Override
	public boolean add(E e) {
		maybeCallInit(e);
		maybeLoad();
		boolean result = this.internalBag.add(e);
		if (result) {
			addInternal(e);
		}
		return result;
	}


}
