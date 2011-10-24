package org.opaeum.runtime.domain;



public interface TinkerCompositionNode extends TinkerNode {
	TinkerCompositionNode getOwningObject();
	void init(TinkerCompositionNode owner);
	/**
	 * Removes this instance from its owningObject, thus ensuring that this
	 * object will not be re-saved accidentally. In addition it also removes
	 * this object from any other navigable collections to ensure that the
	 * object graph accurately reflects the expected state of the database
	 * 
	 * @see addToOwningObject()
	 */
	void removeFromOwningObject();
	void markDeleted();
}
