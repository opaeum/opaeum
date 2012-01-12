package org.nakeduml.runtime.domain;

import org.opaeum.runtime.domain.IPersistentObject;

import com.tinkerpop.blueprints.pgm.Vertex;

public interface TinkerNode extends IPersistentObject {
	Vertex getVertex();
	boolean isTinkerRoot();
}
