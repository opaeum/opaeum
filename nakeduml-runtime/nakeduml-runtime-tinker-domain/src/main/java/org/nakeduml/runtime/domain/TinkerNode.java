package org.nakeduml.runtime.domain;

import com.tinkerpop.blueprints.pgm.Vertex;

public interface TinkerNode {
	Vertex getVertex();
	boolean isTinkerRoot();
	String getUid();
	Long getId();
}
