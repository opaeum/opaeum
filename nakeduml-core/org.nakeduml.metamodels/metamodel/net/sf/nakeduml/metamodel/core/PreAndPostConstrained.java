package net.sf.nakeduml.metamodel.core;
import java.util.Collection;
/**
 * A common interface for all elements that could have preconditions and/or postconditions.
 * @author abarnard
 *
 */
public interface PreAndPostConstrained extends INakedElement{
	Collection<INakedConstraint> getPostConditions();
	Collection<INakedConstraint> getPreConditions();
	INakedClassifier getContext();

}