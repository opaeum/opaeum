package org.nakeduml.runtime.domain;

import java.util.Set;

public interface Constrained{
	Set<String> getFailedInvariants();
}
