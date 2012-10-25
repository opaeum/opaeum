package org.opaeum.runtime.domain;

import java.util.Set;

public interface IConstrained{
	Set<String> getFailedInvariants();
}
