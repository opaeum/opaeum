package org.nakeduml.runtime.domain;
import java.io.Serializable;
public interface AbstractEnum extends Serializable {
	String getPersistentName();
	int getNakedUmlId();
}