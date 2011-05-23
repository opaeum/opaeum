package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.Set;

public interface AbstractEntity extends Serializable {
	Long getId();
	
	void setId(Long id);
	String getName();
	String getUid();
	int getObjectVersion();
}
