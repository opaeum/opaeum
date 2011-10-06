package org.opeum.runtime.domain;

import java.util.Set;

public interface IConstrained{
	Set<String> getFailedInvariants();
}
