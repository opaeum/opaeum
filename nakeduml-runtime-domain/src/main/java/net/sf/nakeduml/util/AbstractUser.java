package net.sf.nakeduml.util;

import java.util.Collection;

public interface AbstractUser{
	String getUserName();
	String getPassword();
	Collection<? extends AbstractUserRole> getRoles();
}
