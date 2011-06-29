package org.nakeduml.nakeduml.tinker.runtime;

import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jVertex;

public class TinkerIdUtil {

	public static Long getId(Vertex v) {
		return (Long)((Neo4jVertex)v).getId();
	}
	
	public static void setId(Vertex v, Long id) {
		throw new IllegalStateException("Id can not be set using Neo4j");
	}
	
	
	public static int getVersion(Vertex v) {
		return -1;
	}	
	
}
