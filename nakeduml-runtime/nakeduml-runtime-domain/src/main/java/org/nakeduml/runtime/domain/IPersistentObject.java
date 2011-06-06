package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.Set;

public interface IPersistentObject extends Serializable {
	Long getId();
	
	void setId(Long id);
	String getName();
	String getUid();
	int getObjectVersion();
}
