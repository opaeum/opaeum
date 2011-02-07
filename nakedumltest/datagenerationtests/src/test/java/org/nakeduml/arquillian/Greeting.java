package org.nakeduml.arquillian;

import javax.ejb.Stateless;

@Stateless
public class Greeting {

	public String sayHallo() {
		return "halo";
	}
	
}
