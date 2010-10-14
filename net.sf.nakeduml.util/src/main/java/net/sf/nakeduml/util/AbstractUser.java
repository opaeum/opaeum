package net.sf.nakeduml.util;

import java.util.Collection;

public interface AbstractUser{
	String getUsername();
	String getPassword();
	Collection<? extends AbstractUserRole> getRoles();
}
