package org.opaeum.runtime.domain;


public interface CompositionNode  {
	CompositionNode getOwningObject();
	void init(CompositionNode owner);
	/**
	 * Removes this instance from its owningObject, thus ensuring that this
	 * object will not be re-saved accidentally. In addition it also removes
	 * this object from any other navigable collections to ensure that the
	 * object graph accurately reflects the expected state of the database
	 * 
	 * @see addToOwningObject()
	 */
	void removeFromOwningObject();
	/**
	 * Adds this instance to its owningObject, thus ensuring that this object is
	 * now attached to the containment tree and ensuring that this object will
	 * be persisted
	 * 
	 * @see removeFromOwningObject()
	 */
	void addToOwningObject();
	void markDeleted();
}
