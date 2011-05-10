package org.nakeduml.runtime.domain;

import java.util.Collection;

public interface AbstractUser{
	String getUserName();
	String getPassword();
	Collection<? extends AbstractUserRole> getRoles();
}
