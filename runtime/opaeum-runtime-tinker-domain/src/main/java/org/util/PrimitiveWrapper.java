package org.util;

import java.util.UUID;

import org.nakeduml.runtime.domain.BaseTinker;
import org.nakeduml.tinker.runtime.GraphDb;

public class PrimitiveWrapper extends BaseTinker {
	
	private static final long serialVersionUID = -7929134057412601537L;
	private Object o;
	private String uid;
	
	public PrimitiveWrapper(Object o, String persistentName) {
		super();
		this.vertex = GraphDb.getDb().addVertex(persistentName);
		defaultCreate();
		this.o = o;
		this.vertex.setProperty("value", o);
	}

	public Object getObject() {
		return o;
	}
	
	@Override
	public boolean isTinkerRoot() {
		return false;
	}

	@Override
	public Long getId() {
		return (Long)this.getVertex().getId();
	}

	@Override
	public void setId(Long id) {
		throw new IllegalStateException("Id can not be set using Neo4j");
	}

	@Override
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}

	@Override
	public int getObjectVersion() {
		return -1;
	}

}
