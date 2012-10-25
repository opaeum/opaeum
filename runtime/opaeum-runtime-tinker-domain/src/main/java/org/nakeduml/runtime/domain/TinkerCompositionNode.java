package org.nakeduml.runtime.domain;

import org.opaeum.runtime.domain.CompositionNode;

public interface TinkerCompositionNode extends TinkerNode, CompositionNode {
	boolean hasInitBeenCalled();
}
