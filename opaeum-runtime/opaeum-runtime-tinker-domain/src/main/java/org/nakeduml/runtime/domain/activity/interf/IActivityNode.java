package org.nakeduml.runtime.domain.activity.interf;

import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.runtime.domain.activity.NodeStat;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.nakeduml.runtime.domain.activity.Token;

public interface IActivityNode<IN extends Token, OUT extends Token> extends INamedElement {
	boolean mayContinue();
	Boolean processNextStart() throws NoSuchElementException;
	List<? extends IActivityEdge<? extends IN>> getIncoming();
	List<? extends IActivityEdge<OUT>> getOutgoing();
	boolean isEnabled();
	NodeStatus getNodeStatus();
	boolean isComplete();
	NodeStat getNodeStat();
	List<? extends Token> getInTokens();
	List<?> getInTokens(String inFlowName);
	List<?> getOutTokens();
	List<?> getOutTokens(String outFlowName);
	
}
