package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.util.TinkerFormatter;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseTinker implements Serializable{

	private static final long serialVersionUID = 3751023772087546585L;
	protected Vertex vertex;
	
	public BaseTinker() {
		super();
	}
	public Vertex getVertex() {
		return vertex;
	}
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	public Date getCreatedOn() {
		return TinkerFormatter.parse((String)this.vertex.getProperty("createdOn"));
	}

	public void setCreatedOn(Date createdOn) {
		this.vertex.setProperty("createdOn", TinkerFormatter.format(createdOn));
	}

	public Date getUpdatedOn() {
		return TinkerFormatter.parse((String)this.vertex.getProperty("updatedOn"));
	}

	public void setUpdatedOn(Date updatedOn) {
		this.vertex.setProperty("updatedOn", TinkerFormatter.format(updatedOn));
	}

	public void defaultCreate() {
		setCreatedOn(new Timestamp(System.currentTimeMillis()));
		setUpdatedOn(new Timestamp(System.currentTimeMillis()));
	}

	public void defaultUpdate() {
		setUpdatedOn(new Timestamp(System.currentTimeMillis()));
	}

}
