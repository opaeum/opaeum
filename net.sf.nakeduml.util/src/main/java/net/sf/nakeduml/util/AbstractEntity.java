package net.sf.nakeduml.util;

import java.io.Serializable;

public interface AbstractEntity extends Serializable {
	Long getId();
	void setId(Long id);
	String getName();
}
