package net.sf.nakeduml.metamodel.activities;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedElement;
public interface INakedActivityNode extends INakedElement {
	INakedActivity getActivity();
	INakedActivityPartition getInPartition();
	void setInPartition(INakedActivityPartition s);
	Set<INakedActivityEdge> getOutgoing();
	Set<INakedActivityEdge> getIncoming();
	/**
	 * Returns all the outgoing edges for this node and all its contained pins
	 * @return
	 */
	Set<INakedActivityEdge> getAllEffectiveOutgoing();
	/**
	 * Returns all the incoming edges for this node and all its contained pins
	 * @return
	 */
	Set<INakedActivityEdge> getAllEffectiveIncoming();
	void addIncoming(INakedActivityEdge edge);
	void addOutgoing(INakedActivityEdge edge);
	Set<INakedActivityEdge> getConditionalOutgoing();
	Set<INakedActivityEdge> getDefaultOutgoing();
	boolean isImplicitFork();
	boolean isImplicitDecision();
	boolean isImplicitJoin();
	INakedStructuredActivityNode getInStructuredNode();	
	
}
