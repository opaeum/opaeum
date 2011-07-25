package net.sf.nakeduml.metamodel.core;
import java.util.Collection;
/**
 * A common interface for all elements that could have preconditions and/or postconditions.
 * @author abarnard
 *
 */
public interface PreAndPostConstrained extends INakedElement{
	void addPreCondition(INakedConstraint precondtions);
	void addPostCondition(INakedConstraint precondtions);
	Collection<INakedConstraint> getPostConditions();
	void setPostConditions(Collection<INakedConstraint> list);
	Collection<INakedConstraint> getPreConditions();
	void setPreConditions(Collection<INakedConstraint> list);
	INakedClassifier getContext();

}