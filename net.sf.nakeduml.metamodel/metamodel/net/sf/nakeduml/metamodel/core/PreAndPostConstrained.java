package net.sf.nakeduml.metamodel.core;
import java.util.Collection;

import nl.klasse.octopus.oclengine.IOclContext;
/**
 * A common interface for all elements that could have preconditions and/or postconditions.
 * @author abarnard
 *
 */
public interface PreAndPostConstrained extends INakedElement{
	void addPreCondition(IOclContext precondtions);
	void addPostCondition(IOclContext precondtions);
	Collection<IOclContext> getPostConditions();
	void setPostConditions(Collection<IOclContext> list);
	Collection<IOclContext> getPreConditions();
	void setPreConditions(Collection<IOclContext> list);
	INakedClassifier getContext();

}