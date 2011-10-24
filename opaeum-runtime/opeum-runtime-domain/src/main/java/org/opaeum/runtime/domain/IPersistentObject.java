package org.opaeum.runtime.domain;

import java.io.Serializable;

public interface IPersistentObject extends Serializable {
	Long getId();
	void setId(Long id);
	String getName();
	String getUid();
	int getObjectVersion();
}
