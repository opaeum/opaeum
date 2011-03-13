package net.sf.nakeduml.userinteractionmetamodel;

import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;

public interface Navigation {
	public UserInteraction getResultingUserInteraction();
	
	public SecurityOnUserAction getSecurityOnView();
	
	public String toXmlString();

}