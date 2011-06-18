package org.nakeduml.runtime.domain;

import com.tinkerpop.blueprints.pgm.Vertex;

public interface TinkerNode extends AbstractEntity {
	Vertex getVertex();
	boolean isTinkerRoot();
//	String getUid();
//	Long getId();
//	void setId(Long id);
//	int getVersion();
}
