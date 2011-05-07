package org.nakeduml.runtime.domain;

import com.tinkerpop.blueprints.pgm.Vertex;

public interface TinkerAuditableNode extends TinkerNode {
	Vertex getMostRecentAuditVertex();
	void createAuditVertex(boolean createParentVertex);
	Vertex getAuditVertex();
}
