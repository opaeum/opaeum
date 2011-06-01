package org.nakeduml.runtime.domain;

import java.util.Collection;

public class NakedUmlUser{
	private String username;
	private Collection<? extends IUserInRole> roles;

	public String getUsername(){
		return this.username;
	}
	public Collection<? extends IUserInRole> getRoles(){
		return this.roles;
	}
}
