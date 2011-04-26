package org.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseTinkerAuditable implements Serializable{

	private static final long serialVersionUID = 3751023772087546585L;
	protected Vertex vertex;
	
	public BaseTinkerAuditable() {
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

	public Date getDeletedOn() {
		return TinkerFormatter.parse((String)this.vertex.getProperty("deletedOn"));
	}
	public void setDeletedOn(Date deletedOn) {
		this.vertex.setProperty("deletedOn", TinkerFormatter.format(deletedOn));
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
		setDeletedOn(new Timestamp(1000L*60*60*24*365*1000));
	}

	public void defaultUpdate() {
		setUpdatedOn(new Timestamp(System.currentTimeMillis()));
	}

}
