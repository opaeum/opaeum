package org.nakeduml.runtime.domain;

import java.util.Collection;

public class NakedUmlUser{
	private String username;
	private Collection<? extends AbstractUserRole> roles;

	public String getUsername(){
		return this.username;
	}
	public Collection<? extends AbstractUserRole> getRoles(){
		return this.roles;
	}
}
