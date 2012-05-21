package org.util;

import com.tinkerpop.blueprints.pgm.Vertex;

public class TinkerIdUtil {

	public static Long getId(Vertex v) {
		return (Long)v.getId();
	}
	
	public static void setId(Vertex v, Long id) {
		throw new IllegalStateException("Id can not be set using Neo4j");
	}
	
	public static int getVersion(Vertex v) {
		return -1;
	}	
	
}
