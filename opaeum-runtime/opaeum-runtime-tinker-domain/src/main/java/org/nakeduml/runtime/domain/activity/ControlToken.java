package org.nakeduml.runtime.domain.activity;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Vertex;

public class ControlToken {

	private Vertex vertex;

	public ControlToken(String edgeName) {
		this.vertex = GraphDb.getDb().addVertex("ControlToken");
		setEdgeName(edgeName);
	}

	public ControlToken(Vertex vertex) {
		this.vertex = vertex;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public String getEdgeName() {
		return (String) this.vertex.getProperty("edgeName");
	}

	public void setEdgeName(String edgeName) {
		this.vertex.setProperty("edgeName", edgeName);
	}

}
