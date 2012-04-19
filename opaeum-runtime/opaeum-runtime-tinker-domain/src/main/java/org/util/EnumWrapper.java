package org.util;

import java.util.UUID;

import org.nakeduml.runtime.domain.BaseTinker;
import org.nakeduml.tinker.runtime.GraphDb;

public class EnumWrapper extends BaseTinker {
	
	private static final long serialVersionUID = -7929134057412601537L;
	private Enum<?> e;
	private String uid;
	
	public EnumWrapper(Enum<?> e, String persistentName) {
		super();
		this.vertex = GraphDb.getDb().addVertex(persistentName);
		defaultCreate();
		this.e = e;
		this.vertex.setProperty("value", e.name());
	}

	public Enum<?> getE() {
		return e;
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

	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
		
	}

}
