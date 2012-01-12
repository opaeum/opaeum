package org.nakeduml.tinker.collection;

import java.util.HashSet;
import java.util.Set;

import org.nakeduml.runtime.domain.TinkerCompositionNode;

public class TinkerSetImpl<E> extends BaseSet<E> implements TinkerSet<E> {

	public TinkerSetImpl(TinkerCompositionNode owner, String label, boolean isInverse, boolean isManyToMany, boolean composite) {
		super();
		this.internalCollection = new HashSet<E>();
		this.owner = owner;
		this.vertex = owner.getVertex();
		this.label = label;
		this.parentClass = owner.getClass();
		this.inverse = isInverse;
		this.manyToMany = isManyToMany;
		this.composite = composite;
	}
	
	public Set<E> getInternalSet() {
		return (Set<E>) this.internalCollection;
	}

}
